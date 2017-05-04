package calculator;

public abstract class Operator
{
	int prio;
	public abstract double apply(double a, double b);
}

class Plus extends Operator
{
	public Plus()
	{
		this.prio = 1;
	}
	
	public double apply(double a, double b)
	{
		return a+b;
	}
}

class Minus extends Operator
{
	
	
	public Minus()
	{
		this.prio = 1;
		
	}
	public double apply(double a, double b)
	{
		return a-b;
	}
}

class Mal extends Operator
{
	public Mal()
	{
		this.prio = 2;
	}
	public double apply(double a, double b)
	{
		return a*b;
	}
}

class Geteilt extends Operator
{
	public Geteilt()
	{
		this.prio = 2;
	}
	public double apply(double a, double b)
	{
		return a/b;
	}
}

class Potenz extends Operator
{
	public Potenz()
	{
		this.prio = 3;
	}
	
	public double apply(double a, double b)
	{
		return Math.pow(a, b);
	}
	
}