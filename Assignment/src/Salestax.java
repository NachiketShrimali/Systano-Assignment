

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Salestax {
	String fn="E:\\input.csv";
	static String fo="E:\\output.csv";
	float x=0;
	boolean checkExistance(String filename)
	{
		File f= new File(filename);
		return f.exists();
		
	}
	void readFile(String filename)
	{

		String s=null;
		try(FileReader fr = new FileReader(filename);BufferedReader br=new BufferedReader(fr))
		{
			int x=0;
			while ((s=br.readLine())!=null) {
				if(x==0)
				{
					x++;
					continue;
				}
				String tokens[]=s.split(",");
				int up=Integer.parseInt(tokens[2]);
				String q=tokens[3];
				
				float total=getTotal(up, q);
				String finalstr=s+","+total+","+(up+total);
				System.out.println("Final data= "+finalstr);
				writeFile(fo, finalstr);
				
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	float getTotal(int up, String q)
	{
		
		if(q.equalsIgnoreCase("india"))
			x=(up/100)*5;
		else if (q.equalsIgnoreCase("japan")) {
			x=(up/100)*10;
		}else if (q.equalsIgnoreCase("egypt")) {
			x=(up/100)*15;
		}
		return x;
	}
	void writeFile(String filename, String finalstr)
	{
		try(FileWriter fw = new FileWriter(filename,true);BufferedWriter bw = new BufferedWriter(fw)){
			
		
			bw.append(System.lineSeparator()+finalstr);
			bw.flush();
			
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Salestax f=new Salestax();
		String fn="E:\\input.csv";
		String head="id,name,price,country,salestax,finalprice";
		boolean ex=f.checkExistance(fn);
		if(ex!=true)
			System.out.println("invalid path");
		else
			{
			f.writeFile(fo,head);
			f.readFile(fn);
			System.out.println("Data Updated Succesfully");
			}
}
}
