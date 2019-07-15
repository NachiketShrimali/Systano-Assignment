import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class FileAddition {
	
	boolean checkExistance(String filename)
	{
		File f= new File(filename);
		return f.exists();
		
	}
	ArrayList<String> readFile(String filename)
	{
		
		ArrayList<String> list =new ArrayList<String>();
		String s=null;
		try(FileReader fr = new FileReader(filename);BufferedReader br=new BufferedReader(fr))
		{
			while ((s=br.readLine())!=null) 
			{
				String tokens[]=s.split(" ");
				list.addAll(Arrays.asList(tokens));
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return list;
	}
	void writeFile(String filename, ArrayList<String> finallist)
	{
		try(FileWriter fw = new FileWriter(filename,true);BufferedWriter bw = new BufferedWriter(fw)){
			bw.write(finallist+System.lineSeparator());
			bw.flush();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {
		String fn1[]= {"E:\\Name1.txt","E:\\Name2.txt","E:\\Name3.txt"};
		FileAddition fa=new FileAddition();
		ArrayList<String> list1=new ArrayList<String>();
		
		for(String i:fn1)
		list1.addAll(fa.readFile(i));
		System.out.println(list1);
		
		ArrayList<String> finallist=new ArrayList<String>();
		
		finallist=(ArrayList<String>) list1.parallelStream().map(x->x.toLowerCase()).distinct().sorted().collect(Collectors.toList());
		System.out.println(finallist);
		fa.writeFile("E:\\final.txt",finallist);
	}

}
