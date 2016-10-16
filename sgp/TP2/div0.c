#include <stdio.h>
#include <signal.h>
#include <unistd.h>
#include <setjmp.h>
#include <stdlib.h>

void tacheron( int sig , siginfo_t *siginfo, void *context) {
  printf("Division par 0\n");
  exit(EXIT_SUCCESS);
}
int main(void) {
  int i, j;
  struct sigaction attraper;
  printf("Entrez le numérateur\n");
  scanf("%d", &i);
  printf("Entrez le dénumérateur\n");
  scanf("%d", &j);
  attraper.sa_sigaction=&tacheron;
  attraper.sa_flags=SA_SIGINFO;
  sigaction(SIGFPE,&attraper,NULL);
  i = i / j; // ICI c'est l'opération assembleur de division qui déclenchera le signal SIGFPE
  printf("Voici le résultat de la division euclidienne i = %d\n",i);
  return EXIT_SUCCESS;
}
