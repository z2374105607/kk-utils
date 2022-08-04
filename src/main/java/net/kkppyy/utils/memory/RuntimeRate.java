package net.kkppyy.utils.memory;

/**
 * <p>Title:RuntimeRate</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author 张凯
 *
 * @date 2021年3月30日 上午11:08:57
 */

public class RuntimeRate {
	/**
	 * 获取系统内存使用情况
	 * @return
	 */
	public static double getMemeryRate() {
		long totalMemory = Runtime.getRuntime().totalMemory()/1024/1024;
		long maxMem = Runtime.getRuntime().maxMemory()/1024/1024;
        long freeMem = Runtime.getRuntime().freeMemory()/1024/1024;
        long use=totalMemory-freeMem;
        System.out.println("当前虚拟机使用情况-总内存："+totalMemory+"M---最大内存:"+maxMem+"M---空闲内存:"+freeMem+"M");
        double rate=((double) use/ (double) maxMem)*100;
		return rate;
	}
	/**
	 * 获取内存的空闲
	 * @return
	 */
	public static long getFree() {
		long totalMemory = Runtime.getRuntime().totalMemory()/1024/1024;
		long maxMem = Runtime.getRuntime().maxMemory()/1024/1024;
		long freeMem = Runtime.getRuntime().freeMemory()/1024/1024;
		long use=totalMemory-freeMem;
		System.out.println("当前虚拟机使用情况-总内存："+totalMemory+"M---最大内存:"+maxMem+"M---空闲内存:"+freeMem+"M");
		long free=maxMem-use;
		return free;
	}
}
