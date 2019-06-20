
#include "op.h"
#include <dlfcn.h>
#include <stdio.h>

typedef int (*CACULATE_FUNC)(int, int);

int main(int argc, char* argv[])
{

    dlerror();
    char* error_msg;

    void* handle = dlopen("libop.so", RTLD_NOW);
    if (handle == 0) {
        printf("load error\n");
        return -1;
    }

    if ((error_msg = dlerror()) != 0) {
        printf("error msg %s\n", error_msg);
        return -1;
    }

    printf("load success\n");

    void* sym_add = dlsym(handle, "add");
    if (sym_add == 0) {
        printf("dlsym error\n");

        if ((error_msg = dlerror()) != 0) {
            printf("error msg %s\n", error_msg);
            return -1;
        }

        return -1;
    }

    void* sym_mul= dlsym(handle, "mul");
    if (sym_mul == 0) {
        printf("dlsym error\n");

        if ((error_msg = dlerror()) != 0) {
            printf("error msg %s\n", error_msg);
            return -1;
        }

        return -1;
    }

    CACULATE_FUNC add_func = sym_add;
    CACULATE_FUNC mul_func = sym_mul;

    printf("%d + %d = %d\n", 1, 11, add_func(11, 1));
    printf("%d x %d = %d\n", 10, 10, mul_func(10, 10));

    dlclose(handle);

    return 0;
}
