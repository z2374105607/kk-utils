package net.kkppyy.utils.runExe;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RuntimeExe {
	private static Logger logger = LoggerFactory.getLogger(RuntimeExe.class);

	public static void runExe(String exePath) throws InterruptedException, IOException {
		String ls_1;
		Process process = Runtime.getRuntime().exec(exePath);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		while ((ls_1 = bufferedReader.readLine()) != null)
			System.out.println(ls_1);
		process.waitFor();
	}

	public static String exeCmd(String commandLine) {
		logger.info("exeLinux.exeCmd-->" + commandLine);
		BufferedReader br = null;
		try {
			File file = new File("C:\\temp");
			File tmpFile = new File("C:\\temp\\temp.tmp");// 新建一个用来存储结果的缓存文件
			if (!file.exists()) {
				file.mkdirs();
			}
			if (!tmpFile.exists()) {
				tmpFile.createNewFile();
			}
			ProcessBuilder pb = new ProcessBuilder().command("cmd.exe", "/c", commandLine).inheritIO();
			pb.redirectErrorStream(true);// 这里是把控制台中的红字变成了黑字，用通常的方法其实获取不到，控制台的结果是pb.start()方法内部输出的。
			pb.redirectOutput(tmpFile);// 把执行结果输出。
			pb.start().waitFor();// 等待语句执行完成，否则可能会读不到结果。
			InputStream in = new FileInputStream(tmpFile);
			br = new BufferedReader(new InputStreamReader(in,"GBK"));
			String line = null;
			StringBuffer str = new StringBuffer();
			while ((line = br.readLine()) != null) {
				str.append(line).append("\r\n");
			}
			br.close();
			br = null;
			tmpFile.delete();
			//str.
			return str.toString();
		} catch (Exception e) {
			logger.error("exeCmd执行异常：", e);
			return e.getMessage();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					logger.error("exeCmd关闭流异常：", e);
				}
			}
		}
	}

	public static Process exeCmdPid(String... commandLine) throws IOException {

		// create new process builder
		ProcessBuilder pb = new ProcessBuilder(commandLine);

		// start the process
		Process p = pb.start();

		// wait for the process to complete
		// int exitCode = p.waitFor();

		// print the exit code
		// System.out.println("External program exited with code " + exitCode);

		// destroy the process
		// p.destroy();
		return p;
	}

	public static String exeCmdKill(Process p) {

		// destroy the process
		p.destroy();
		return p.toString();
	}

	public static String exeLinux(String commandLine) {
		logger.info("exeLinux.commandLine-->" + commandLine);
		Process process = null;
		InputStreamReader inputStreamReader = null;
		InputStreamReader errorStreamReader = null;
		LineNumberReader consoleInput = null;
		LineNumberReader consoleError = null;
		String consoleInputLine = "";
		String consoleErrorLine = "";

		StringBuilder exeResult = new StringBuilder();
		StringBuilder exeErrorResult = new StringBuilder();
		try {
			process = Runtime.getRuntime().exec(new String[] { "/bin/sh", "-c", commandLine });
			// 获取标准输出
			inputStreamReader = new InputStreamReader(process.getInputStream());
			consoleInput = new LineNumberReader(inputStreamReader);
			while ((consoleInputLine = consoleInput.readLine()) != null) {
				exeResult.append(consoleInputLine).append("\r\n");
			}
			// 获取错误输出
			errorStreamReader = new InputStreamReader(process.getErrorStream());
			consoleError = new LineNumberReader(errorStreamReader);
			while ((consoleErrorLine = consoleError.readLine()) != null) {
				exeErrorResult.append(consoleErrorLine).append("\r\n");
			}
			// 回调返回0时命令执行正常
			int resultCode = process.waitFor();
			if (resultCode > 0) {
				return exeErrorResult.toString();
			} else {
				return exeResult.toString();
			}
		} catch (Exception e) {
			logger.error("exeLinux执行命令异常：", e);
			return e.getMessage();
		} finally {
			try {
				if (null != consoleInput) {
					consoleInput.close();
				}
				if (null != consoleError) {
					consoleError.close();
				}
				if (null != inputStreamReader) {
					inputStreamReader.close();
				}
				if (null != errorStreamReader) {
					errorStreamReader.close();
				}
				if (null != process) {
					process.destroy();
					process = null;
				}
			} catch (Exception e) {
				logger.error("exeLinux关闭流异常：", e);
			}
		}
	}
}
