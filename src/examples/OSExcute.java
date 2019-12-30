package examples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class OSExcute {
	public static void command(String command){
		boolean err = false;
		try {
			String[] commands = command.split(" ");
			System.out.println(Arrays.toString(commands));
			Process process = new ProcessBuilder((commands)).start();
			BufferedReader results = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String s;
			while((s = results.readLine()) != null)
				System.out.println(s);
			BufferedReader errors = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			//report errors and return nonzero value to calling process if there are problems
			while((s = errors.readLine()) != null){
				System.err.println(s);
				err = true;
			}
		} catch (Exception e) {
			//Compensate for Windows 2000,which throws an exception for the default command line:
			if(command.startsWith("CMD /C"))
				command("CMD /C" + command);
			else
				throw new RuntimeException(e);
		};
		if(err)
			throw new OSExcuteException("Errors excuting " + command);
	}
	public static void main(String[] args) throws IOException {
		Process process = new ProcessBuilder("javap","E:\\学习\\thinkInJava_at_git\\enum\\bin\\examples\\OSExcuteException.class").start();
		InputStream in = process.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String a;
		while((a = br.readLine()) != null) {
			System.out.println(a);
		}
		
		command("javap E:\\学习\\thinkInJava_at_git\\enum\\bin\\examples\\OSExcuteException.class");
	}
}
