cmake_minimum_required(VERSION 3.4.1)

set(cpp_DIR ${CMAKE_SOURCE_DIR})

add_library(hello-jni SHARED
            fake_dlfcn.cpp
            hello.c
            )

add_library(op-jni SHARED
            op.c
            )

# Include libraries needed for hello-jni lib
target_link_libraries(hello-jni
                      android
                      log
                      )

target_link_libraries(op-jni
                      android
                      log
                      )