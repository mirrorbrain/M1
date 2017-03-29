
#include "userlib/syscall.h"


#include "userlib/libnachos.h"

int main() {
  OpenFileId f;
  int i;
  int buf[1];


  n_printf("test writing\n");
  Create("testMmap",1024);
  f = Open("testMmap");
  for (i=0;i<20;i++) {
    buf[0] = i;
    n_printf("%d ",i);
    Write((char *)buf,sizeof(int),f);
  }
  n_printf("\n test read \n");
  Close(f);
  f = Open("testMmap");
  for (i=0;i<20;i++) {
    Read((char *)buf,sizeof(int),f);
    n_printf("%d ",buf[0]);
    if (buf[0]!=i) n_printf("ERREUR\n");
  }
  Close(f);
  n_printf("OK \n");

  // Test mapped files
  f = Open("testMmap");
  int *addr = (int *)Mmap(f,100*sizeof(int));
  n_printf("file in memory\n");
  n_printf("   Address map %x\n",addr);
  if ((int)addr ==-1) {
    n_printf("Mmap failed\n");
    return -1;
  }
  n_printf("Writing into mapped file:\n");
  n_printf("blabla car : %d \n", addr);
  for (i=0;i<100;i++) {
    addr[i] = -1*i;
  }
  n_printf("   Checking contents\n");
  for (i=0;i<100;i++) {
    if (addr[i] != -1*i) {
      n_printf(" Not OK\n");
      return -1;
    }
    else n_printf("%d ",addr[i]);
  }
  n_printf("   OK\n");

/*
  n_printf("   Test of file contents: writing file\n");
  err("tst_file(Open)",f = Open("mmap"));
  n_printf("   Checking contents\n");
  err("tst_file(Close)",Close(f));
  err("tst_file(Open)",f = Open("mmap"));
  for (i=0;i<100;i++) {
    err("tst_file(Read)",Read((char *)buf,sizeof(int),f));
    n_printf("%d ",buf[0]);
    if (buf[0]!=-1*i) n_printf("   Contents NOK\n");
  }
  err("tst_file(Close)",Close(f));
  n_printf("   Contents OK\n");
*/
  return 0;
}