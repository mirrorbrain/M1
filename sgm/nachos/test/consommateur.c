#include "userlib/syscall.h"
#include "userlib/libnachos.h"

#define TAILLE 256

void routine_reception(){
	char s[TAILLE];
	int nb_recu;

	if((nb_recu = TtyReceive(s, TAILLE)) < 0){
		PError("Failled Reception de la chaine");
		Exit(1);
	}

	n_printf("Succes Chaine reçue avec : %d caractere(s)\n", nb_recu);
	n_printf("Chaine reçue : \n%s\n", s);
}



int main (){
  char * threadName = "threadName";
  ThreadId thread1, thread2, thread3;
  if((thread1 = threadCreate(threadName,routine_reception))==-1){
    PError("creation thread 1 failled");
  }

  if(Join(thread1)==-1){
    PError("liberation thread 1 failled");
  }


  if((thread2 = threadCreate(threadName,routine_reception))==-1){
    PError("creation thread 2 failled");
  }

  if(Join(thread2)==-1){
    PError("liberation thread 2 failled");
  }


  if((thread3 = threadCreate(threadName,routine_reception))==-1){
    PError("creation thread 3 failled");
  }

  if(Join(thread3)==-1){
    PError("liberation thread 3 failled");
  }


  n_printf("Exiting main\n");
  return 0;
}
