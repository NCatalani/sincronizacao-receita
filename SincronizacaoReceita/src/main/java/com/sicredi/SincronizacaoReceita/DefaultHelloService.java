package com.sicredi.SincronizacaoReceita;

public class DefaultHelloService implements HelloService {

    @Override
    public void hello() {
        System.out.println("Hello from Hello Service");
    }
}