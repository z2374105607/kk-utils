package net.kkppyy.utils.runExe;

import java.io.IOException;

import org.junit.Test;

public class RuntimeExeTest {
	@Test
	public void runExeTest() throws InterruptedException, IOException {
		RuntimeExe.runExe("cmd.exe /c F:\\24.jdk\\jdk_17.0.4\\bin\\java.exe -version");
	}

	@Test
	public void exeCmdTest() throws InterruptedException, IOException {
		// String result=RuntimeExe.exeCmd("F:\\24.jdk\\jdk_17.0.4\\bin\\java.exe
		// -version");
		// String
		// result=RuntimeExe.exeCmd("F:\\04.VScode\\24.ifcTransform\\CRCCPlatform\\CRCCPlatform\\Debug\\CRCCPlatform.exe
		// D:\\CRCCPlatform\\testfiles D:\\CRCCPlatform\\output");
		String result = RuntimeExe
				.exeCmd("F:\\28.UE\\U02youxi\\U02youxistream\\Windows\\U02youxi\\Binaries\\Win64\\U02youxi_ue.exe");
		// String
		// result=RuntimeExe.exeCmd("F:\\04.VScode\\24.ifcTransform\\CRCCPlatform2023-03-25\\CRCCPlatform\\CRCCPlatform.exe
		// D:\\CRCCPlatform\\testfiles D:\\CRCCPlatform\\output");
		// String result=RuntimeExe.exeCmd("curl -k --insecure
		// https://sofa.rebim.cn/sofanew/index.html");
		System.out.println(result);
	}

	@Test
	public void exeCmdPidTest() throws InterruptedException, IOException {
		String [] cmdArray=new String[3];
		cmdArray[0]="F:\\\\28.UE\\\\U02Artist_QuickStart\\\\Windows\\\\U02Artist_QuickStart.exe";
		cmdArray[1]="-PixelStreamingIP=localhost";
		cmdArray[2]="-PixelStreamingPort=8888";
		Process result = RuntimeExe.exeCmdPid(cmdArray);
		System.out.println(result);
	}

	@Test
	public void exeCmdKillTest() throws InterruptedException, IOException {
		String result = RuntimeExe.exeCmd("taskkill /pid 27988  -t  -f");
		System.out.println(result);
	}
}
