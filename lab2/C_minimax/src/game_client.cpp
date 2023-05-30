/****************************
Maciej Gębala (CC BY-NC 4.0)
Client ver. 0.1
2023-03-30
****************************/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <stdbool.h>
#include <arpa/inet.h>
#include <time.h>

#include "board_functions.h"
#include "algorithm.h"

void print_end_msg(int msg){
  switch( msg ) {
    case 1 : printf("You won.\n"); break;
    case 2 : printf("You lost.\n"); break;
    case 3 : printf("Draw.\n"); break;
    case 4 : printf("You won. Opponent error.\n"); break;
    case 5 : printf("You lost. Your error.\n"); break;
  }
}

int main(int argc, char *argv[])
{
  if( argc!=5 ) { printf("Wrong number of arguments\n"); return -1; }
  srand(time(NULL));

  char server_message[16], client_message[16];
  bool end_game = false;
  int msg, move;
  int player = atoi(argv[3]); if (player == 2){player = -1;}
  const int depth = atoi(argv[4]);
  const int port = atoi(argv[2]);

  // Create socket
  int socket_desc = socket(AF_INET, SOCK_STREAM, 0);
  if( socket_desc<0 ) { printf("Unable to create socket\n"); return -1; }

  struct sockaddr_in server_addr;
  server_addr.sin_family = AF_INET;
  server_addr.sin_port = htons(port);
  server_addr.sin_addr.s_addr = inet_addr(argv[1]);

  // Send connection request to server
  if( connect(socket_desc, (struct sockaddr*)&server_addr, sizeof(server_addr))<0 ) { printf("Unable to connect\n"); return -1; }
  printf("Connected with server successfully\n");
  // Receive the server message
  memset(server_message, '\0', sizeof(server_message));
  if( recv(socket_desc, server_message, sizeof(server_message), 0)<0 ) { printf("Error while receiving server's message\n"); return -1; }
  printf("Server message: %s\n",server_message);
  memset(client_message, '\0', sizeof(client_message));
  strcpy(client_message, argv[3]);
  // Send the message to server
  if( send(socket_desc, client_message, strlen(client_message), 0)<0 ) {printf("Unable to send message\n"); return -1; }

  setBoard(); 
  shuffleChildren(); shuffleChildren(); shuffleChildren();
  while( !end_game ) {
    memset(server_message, '\0', sizeof(server_message));
    if( recv(socket_desc, server_message, sizeof(server_message), 0)<0 ) { printf("Error while receiving server's message\n"); return -1; }
    printf("Server message: %s\n", server_message);
    msg = atoi(server_message);
    move = msg%100; msg = msg/100;
    if( move!=0 ) {
      move -= 11;
      setMove(move, -player);
      printBoard();
    }
    if( (msg==0) || (msg==6) ) {
      printf("Your move: ");
      move = alphabetaMove(depth, player);
      setMove(move, player);
      printBoard();
      memset(client_message, '\0', sizeof(client_message));
      sprintf(client_message, "%d", move + 11);
      if( send(socket_desc, client_message, strlen(client_message), 0)<0 ) {
        printf("Unable to send message\n");
        return -1;
      }
      printf("Client message: %s\n", client_message);
     }

     else {
       end_game = true;
       print_end_msg(msg);
     }
   }

  // Close socket
  close(socket_desc);
  return 0;
}
