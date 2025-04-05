package contawcsas;

import java.util.Scanner;

public abstract class Conta {
	    protected double saldo; 
	    private String titular;
	    private int numero;
	    
	    public Conta(double saldo) {
	        this.saldo = saldo;
	    }

	    public abstract void sacar(double valor);

	    public void exibirSaldo() {
	        System.out.printf("Saldo Atual: %.2f%n", saldo);
	    }
	}

	class ContaCorrente extends Conta {
	    private double limite; 
	        
	    public ContaCorrente(double saldo, double limite) {
	        super(saldo);
	        this.limite = limite;
	    }

	    @Override
	    public void sacar(double valor) {
	        double montante = saldo + limite;
	        if (montante >= valor) {
	            saldo -= valor;
	            System.out.println("Saque realizado com sucesso.");
	        } else {
	            System.out.println("Saldo insuficiente!");
	        }
	        exibirSaldo();
	    }
	}

	class ContaPoupanca extends Conta {
	    public ContaPoupanca(double saldo) {
	        super(saldo);
	    }

	    @Override
	    public void sacar(double valor) {
	        if (saldo >= valor) {
	            saldo -= valor;
	            System.out.println("Saque realizado com sucesso.");
	        } else {
	            System.out.println("Saldo insuficiente!");
	        }
	        exibirSaldo();
	    }
	}

	 class Principal {
	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);

	        System.out.print("Digite o tipo de conta (corrente/poupanca): ");
	        String tipoConta = scanner.next();

	        System.out.print("Digite o saldo inicial: ");
	        double saldoInicial = scanner.nextDouble();

	        Conta conta = null; // Inicializa a variável conta

	        if (tipoConta.equalsIgnoreCase("corrente")) {
	            System.out.print("Digite o limite do cheque especial: ");
	            double limite = scanner.nextDouble();
	            conta = new ContaCorrente(saldoInicial, limite);
	        } else if (tipoConta.equalsIgnoreCase("poupanca")) {
	            conta = new ContaPoupanca(saldoInicial);
	        } else {
	            System.out.println("Tipo de conta inválido!");
	            scanner.close();
	            return; // Encerra o programa
	        }

	        
	        while (scanner.hasNextDouble()) {
	            double valorSaque = scanner.nextDouble();
	            if (valorSaque < 0) {
	                break;
	            }
	            conta.sacar(valorSaque);
	        }

	        scanner.close();
	    }
	}

