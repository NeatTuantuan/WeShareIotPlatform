package edu.xd.bdilab.iotplatform.netty.util;


/**
 * @Decription TODO
 * @Author Humphrey
 * @Date 2019/9/22 11:12
 * @Version 1.0
 **/
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class MachineCodeUtils {


    private static String LINUX_OS = "LINUX";

    private static String WINDOWS_OS = "WINDOWS";

    private static String DISK_ID = "diskid";

    private static String CPU_ID = "cpuid";

    private static String MAINBOARD_ID = "mainboard_id";

    /**
     * 获取主板序列号
     * @return
     */
    private static String getMotherboardSn() {
        String result = "";
        try {
            File file = File.createTempFile("realhowto", ".vbs");
            file.deleteOnExit();
            FileWriter fw = new FileWriter(file);

            String vbs = "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")\n"
                    + "Set colItems = objWMIService.ExecQuery _ \n"
                    + "   (\"Select * from Win32_BaseBoard\") \n"
                    + "For Each objItem in colItems \n"
                    + "    Wscript.Echo objItem.SerialNumber \n"
                    + "    exit for  ' do the first cpu only! \n" + "Next \n";

            fw.write(vbs);
            fw.close();
            String path = file.getPath().replace("%20", " ");
            Process p = Runtime.getRuntime().exec(
                    "cscript //NoLogo " + path);
            BufferedReader input = new BufferedReader(new InputStreamReader(
                    p.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                result += line;
            }
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.trim();
    }

    /**
     * 获取硬盘序列号(该方法获取的是 盘符的逻辑序列号,并不是硬盘本身的序列号)
     * 硬盘序列号还在研究中
     * @param drive 盘符
     * @return
     */
    private static String getHardDiskSn(String drive) {
        String result = "";
        try {
            File file = File.createTempFile("realhowto", ".vbs");
            file.deleteOnExit();
            FileWriter fw = new FileWriter(file);

            String vbs = "Set objFSO = CreateObject(\"Scripting.FileSystemObject\")\n"
                    + "Set colDrives = objFSO.Drives\n"
                    + "Set objDrive = colDrives.item(\""
                    + drive
                    + "\")\n"
                    + "Wscript.Echo objDrive.SerialNumber"; // see note
            fw.write(vbs);
            fw.close();
            String path = file.getPath().replace("%20", " ");
            Process p = Runtime.getRuntime().exec(
                    "cscript //NoLogo " + path);
            BufferedReader input = new BufferedReader(new InputStreamReader(
                    p.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                result += line;
            }
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.trim();
    }

    /**
     * 获取CPU序列号
     * @return
     */
    private static String getCpuSerial() {
        String result = "";
        try {
            File file = File.createTempFile("tmp", ".vbs");
            file.deleteOnExit();
            FileWriter fw = new FileWriter(file);
            String vbs = "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")\n"
                    + "Set colItems = objWMIService.ExecQuery _ \n"
                    + "   (\"Select * from Win32_Processor\") \n"
                    + "For Each objItem in colItems \n"
                    + "    Wscript.Echo objItem.ProcessorId \n"
                    + "    exit for  ' do the first cpu only! \n" + "Next \n";

            // + "    exit for  \r\n" + "Next";
            fw.write(vbs);
            fw.close();
            String path = file.getPath().replace("%20", " ");
            Process p = Runtime.getRuntime().exec(
                    "cscript //NoLogo " + path);
            BufferedReader input = new BufferedReader(new InputStreamReader(
                    p.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                result += line;
            }
            input.close();
            file.delete();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        if (result.trim().length() < 1 || result == null) {
            result = "无CPU_ID被读取";
        }
        return result.trim();
    }



    private static String executeLinuxCmd(String cmd)  {
        try {
            System.out.println("got cmd job : " + cmd);
            Runtime run = Runtime.getRuntime();
            Process process;
            process = run.exec(cmd);
            InputStream in = process.getInputStream();
            BufferedReader bs = new BufferedReader(new InputStreamReader(in));
            StringBuffer out = new StringBuffer();
            byte[] b = new byte[8192];
            for (int n; (n = in.read(b)) != -1;) {
                out.append(new String(b, 0, n));
            }

            in.close();
            process.destroy();
            return out.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param cmd 命令语句
     * @param record 要查看的字段
     * @param symbol 分隔符
     * @return
     */
    private static String getSerialNumber(String cmd ,String record,String symbol) {
        String execResult = executeLinuxCmd(cmd);
        String[] infos = execResult.split("\n");

        for(String info : infos) {
            info = info.trim();
            if(info.indexOf(record) != -1) {
                info.replace(" ", "");
                String[] sn = info.split(symbol);
                return sn[1];
            }
        }
        return null;
    }

    /**
     * 获取CPUID、硬盘序列号、MAC地址、主板序列号
     * @return
     */
    private static Map<String, String> getAllSn(){
        String os = System.getProperty("os.name");
        os = os.toUpperCase();
        Map<String, String> snVo = new HashMap<>(3);
        //linux系统
        if(LINUX_OS.equals(os)) {
            //cpu编号
            String cpuid = getSerialNumber("dmidecode -t processor | grep 'ID'", "ID",":");
            //主板编号
            String mainBoardNumber = getSerialNumber("dmidecode |grep 'Serial Number'", "Serial Number",":");
            //磁盘编号
            String diskNumber = getSerialNumber("fdisk -l", "Disk identifier",":");

            snVo.put(CPU_ID,cpuid.toUpperCase().replace(" ", ""));
            snVo.put(DISK_ID,diskNumber.toUpperCase().replace(" ", ""));
            snVo.put(MAINBOARD_ID,mainBoardNumber.toUpperCase().replace(" ", ""));
        }
        //windows系统
        else if(os.contains(WINDOWS_OS)) {
            String cpuid = getCpuSerial();
            String mainboard = getMotherboardSn();
            String disk = getHardDiskSn("c");

            //cpu SN码
            snVo.put(CPU_ID,cpuid.toUpperCase().replace(" ", ""));
            //c盘 SN码
            snVo.put(DISK_ID,disk.toUpperCase().replace(" ", ""));
            //主板 SN码
            snVo.put(MAINBOARD_ID,mainboard.toUpperCase().replace(" ", ""));
        }
        //无法识别系统
        else{
            return null;
        }

        return snVo;
    }


    public static String getMachineCode(){
        Map<String,String> snMap = getAllSn();
        String rawCode = snMap.get(CPU_ID)+"bdilab"+snMap.get(DISK_ID)+"bdilab"+snMap.get(MAINBOARD_ID);
        return DecryptUtils.getMd5(rawCode);
    }

    /**
     * linux
     * cpuid : dmidecode -t processor | grep 'ID'
     * mainboard : dmidecode |grep 'Serial Number'
     * disk : fdisk -l
     * mac : ifconfig -a
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("MachineCode: "+getMachineCode());
    }
}

