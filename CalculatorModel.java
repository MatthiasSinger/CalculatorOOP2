package calculator;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class CalculatorModel
{

    public double calculate(String input) 
    {
    	ArrayDeque<Operator> ops = new ArrayDeque<Operator>();
    	List<Object> ausgabe = new ArrayList<>();
    	getPostfix(input, ops, ausgabe);
    	System.out.println(ausgabe);
		return calculatePostfix(ausgabe);
    }

	private void getPostfix(String input, ArrayDeque<Operator> ops, List<Object> ausgabe)
	{
		
		input = lex(input);
		int start = 0;
    	for (int i = 0; i < input.length(); i++)
    	{
    		if (isOperator(input.charAt(i)))
    		{
    			String s = input.substring(start, i);
    			s = s.replace('#', '-');
    			ausgabe.add(Double.valueOf(s));
    			
    			start = i+1;
    			
    			Operator op = null;
    			switch (input.charAt(i))
    			{
    			case '+': 	op = new Plus();break;
    			case '-':   op = new Minus();break;
    			case '*': 	op = new Mal();;break;
    			case '/': 	op = new Geteilt();break;
    			case '^':   op = new Potenz();break;
    			default: System.err.println("Ungültiger Operator!");
    			}
    			
    			if (ops.size() == 0)
    			{
    				ops.push(op);
    			}
    			else
    			{
    				if (op instanceof Potenz)
    				{
    					if (ops.peekLast().prio >= op.prio)
    					{
    						while (!ops.isEmpty())
    						{
    							ausgabe.add(ops.pop());
    						}
    						ops.push(op);
    					}
    					else
    					{
    						ops.push(op);
    					}
    				}
    				else
    				{
	    				if (ops.peekLast().prio > op.prio)
	    				{
	    					while (!ops.isEmpty())
	    					{
	    						ausgabe.add(ops.pop());
	    					}
	    					ops.push(op);
	    				}
	    				else
	    				{
	    					ops.push(op);
	    				}
    				}
    			}
    		}
    	}
    	String s = input.substring(start);
    	s = s.replace('#', '-');
		ausgabe.add(Double.valueOf(s));
		
		while (!ops.isEmpty())
    	{
    		ausgabe.add(ops.pop());
    	}
	}
	
    private String lex(String input)
	{
    	if(input.charAt(0) == '-')
    	{
    		input = "#" + input.substring(1);
    	}
    	
		for (int i = 1; i < input.length(); i++)
		{
			if (input.charAt(i) == '-' && isOperator(input.charAt(i-1)))
			{
				input = input.substring(0, i) + "#"+input.substring(i+1) ;
			}
		}
		return input;
	}

	private double calculatePostfix(List<Object> postfix)
    {
    	double ergebnis = -1;
    	int index = -1;
    	for (int i = 0; i < postfix.size(); i++)
    	{
    		if (postfix.get(i) instanceof Operator)
    		{
    			index = i;
    			break;
    		}
    	}
    	if (index == -1)
    	{
    		return (double)postfix.get(0);
    	}
    	else
    	{
    		double result = ((Operator)postfix.get(index)).apply((double)postfix.get(index-2), (double)postfix.get(index-1));
    		postfix.set(index, new Double(result));
    		postfix.remove(index-2);
    		postfix.remove(index-2);
    		ergebnis = calculatePostfix(postfix);
    	}
    	return ergebnis;
    }
    
	private boolean isOperator(char c)
	{
		return c =='+' || c == '-' || c == '*' || c == '/' || c == '^';
	}
}
