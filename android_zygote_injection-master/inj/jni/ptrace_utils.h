/*
 * ptrace_utils.c
 *
 *  Created on: 2014-7
 *      Author: long
 * modify from author: boyliang 
 */

#ifndef PTRACE_UTILS_H_
#define PTRACE_UTILS_H_

#define CPSR_T_MASK		( 1u << 5 )

int ptrace_getregs(pid_t pid, struct pt_regs* regs);

int ptrace_setregs(pid_t pid, struct pt_regs* regs);

int ptrace_attach( pid_t pid , int zygote);

int ptrace_detach( pid_t pid );

int ptrace_continue(pid_t pid);

int ptrace_syscall(pid_t pid);

int ptrace_write(pid_t pid, uint8_t *dest, uint8_t *data, size_t size);

int ptrace_read( pid_t pid,  uint8_t *src, uint8_t *buf, size_t size );

int ptrace_call(pid_t pid, uint32_t addr, long *params, int num_params, struct pt_regs* regs);

void* ptrace_dlopen(pid_t target_pid, void* remote_dlopen_addr, const char*  filename);

//int call_so_entry(pid_t target_pid, uint32_t proc,long *params, int num_params, struct pt_regs* regs)�?
uint8_t *map_base;

#endif /* PTRACE_UTILS_H_ */
