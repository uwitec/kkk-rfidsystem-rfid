package demo;

import java.io.*;
import java.net.*;

    public  class RfidOrder
    {
         public  static int IPLength = 5;
         public  static int DataFieldLength = 6;

         private static byte[] ReaderInstrustionTemp;
         private static byte[] ReaderInstrustionClearTemp;
         private static byte[] DataPackageTemp;
         private static byte[] DataPackageClearTemp;
		 private static byte[] DataPackageTemplong;
         private static byte[] DataPackageClearTemplong;

         static int PCInstrustionLength = 6;
         static int ReaderInstrustionLength = 14;
         static int DataPackageLength = 26;
		 static int DataPackageLengthlong = 40;
           RfidOrder()
        {
            ReaderInstrustionTemp = new byte[] 
			{
                (byte)0x47, (byte)0x74, (byte)0x0B,               //起始符
                (byte)0xFF,                           //命令参数
                (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,   //读写器IP
                (byte)0xFF, (byte)0xFF, (byte)0xFF,
                (byte)0x8E, (byte)0xE8    //结尾符
            };
            ReaderInstrustionClearTemp = new byte[] 
            {
                (byte)0x00, (byte)0x00, (byte)0x00,               //起始符
                (byte)0xFF,                           //命令参数
                (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,   //读写器IP
                (byte)0xFF, (byte)0xFF, (byte)0xFF,
                (byte)0x00, (byte)0x00    //结尾符
            };
            DataPackageTemp = new byte[] { 
                (byte)0x47, (byte)0x74, (byte)0x17,                    //起始符
                (byte)0x02,                                //命令参数
                (byte)0x13,                   
                (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,        //读写器IP
                (byte)0xFF, (byte)0xFF, (byte)0xFF,                   
                (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,        //节点IP
                (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,  //数据域
                (byte)0x8E, (byte)0xE8                           //结尾符           
            };
            DataPackageClearTemp = new byte[] { 
                (byte)0x00, (byte)0x00, (byte)0x00,                    //起始符
                (byte)0x00,                                //命令参数
                (byte)0x00,                   
                (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,        //读写器IP
                (byte)0xFF, (byte)0xFF, (byte)0xFF,                   
                (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,        //节点IP
                (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,  //数据域
                (byte)0x00, (byte)0x00                           //结尾符           
            };
			DataPackageTemplong = new byte[] { 
                (byte)0x47, (byte)0x74, (byte)0x17,                    //起始符
                (byte)0x02,                                //命令参数
                (byte)0x13,                   
                (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,        //读写器IP
                (byte)0xFF, (byte)0xFF, (byte)0xFF,                   
                (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,        //节点IP
                (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,  //数据域
				(byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, //数据域
                (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, //数据域
				(byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, //数据域
				(byte)0x8E, (byte)0xE8                           //结尾符           
            };
            DataPackageClearTemplong = new byte[] { 
                (byte)0x00, (byte)0x00, (byte)0x00,                    //起始符
                (byte)0x00,                                //命令参数
                (byte)0x00,                   
                (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,        //读写器IP
                (byte)0xFF, (byte)0xFF, (byte)0xFF,                   
                (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,        //节点IP
                (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,  //数据域
				(byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, //数据域
                (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, //数据域
				(byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, //数据域
                (byte)0x00, (byte)0x00                           //结尾符           
            };
        }
          private static byte[] GetPcInstruction(byte command)
        {
            return new byte[] 
            {
                (byte)0x47, (byte)0x74, (byte)0x03,           //起始
                (byte)command,                    //命令参数
                (byte)0x8E, (byte)0xE8                  //结尾符
            };
        }


        /// <summary>
        /// 判断是否正确读写器指令
        /// </summary>
        /// <param name="readerBuff"></param>
        /// <returns></returns>
          public static boolean IsReaderInstrustion(byte[] readerBuff, int offset)
        {
            if (readerBuff.length < ReaderInstrustionLength)
            {
                return false;
            }
			//System.out.println("readerBuff: "+readerBuff[0]+" "+readerBuff[13]+" offset:"+offset);
			//System.out.println("ReaderInstrustionClearTemp: "+ReaderInstrustionClearTemp[0]+" "+ReaderInstrustionClearTemp[13]);
            for (int i = 0; i < 13; i++)
            {
                if ((readerBuff[offset + i] | ReaderInstrustionClearTemp[i]) != ReaderInstrustionTemp[i])
                {
                    return false;
                }
            }
            return true;
        }
        /// <summary>
        /// 判断是否正确数据包结构
        /// </summary>
        /// <param name="readerBuff"></param>
        /// <returns></returns>
          public static boolean IsDataPackageInstrustion(byte[] readerBuff, int offset)
        {
			  boolean flag = false; 
            if (readerBuff.length < DataPackageLength  && readerBuff.length < DataPackageLengthlong)
            {
                return false;
            }
            for (int i = 0; i < DataPackageLength; i++)
            {
                if ((readerBuff[offset + i] | DataPackageClearTemp[i]) != DataPackageTemp[i])
                {
                    flag = true;
					break;
                }
            }
			if(flag){
				for (int i = 0; i < DataPackageLengthlong; i++)
				{
					if ((readerBuff[offset + i] | DataPackageClearTemplong[i]) != DataPackageTemplong[i])
					{
						return false;		
					}
				}
			}
            return true;
        }

          public static byte[] GetPCInstrustion(PCInstructionType pcInstructiontype)
        {
            switch (pcInstructiontype)
            {
                case Request_Read:
                    return GetPcInstruction((byte)0x02);

                case Request_Edit:
                    return GetPcInstruction((byte)0x03);

                case Request_EndCommunication:
                    return GetPcInstruction((byte)0x04);

                case Confirm_ReadData:
			//System.out.println( "  PCInstructionType  Request_Read"   );
                    return GetPcInstruction((byte)0x06);

                case Confirm_EndCommunication:
                    return GetPcInstruction((byte)0x07);

                default:
                    return GetPcInstruction((byte)0x01);
            }
        }
//////////////////////////////////////////////////////////////////////////////
/*public static void main(String [] args){
		RfidOrder rf= new RfidOrder();
		Client cl = new Client();
		PCInstructionType pcit;
			pcit = PCInstructionType.Confirm_ReadData;
		GetPCInstrustion(pcit);		
		//cl.sendMsg(rf.ReaderInstrustionTemp);
		System.out.println();
		//cl.sendMsg(rf.DataPackageTemp);

	}*/


        /// <summary>
        /// 获得读写器指令
        /// </summary>
        /// <param name="readerBuff"></param>
        /// <returns></returns>
          public static ReaderInstruction GetReaderInstrustion(byte[] readerBuff, int offset)
        {
            ReaderInstruction readerInstrustion = null;
            if (!IsReaderInstrustion(readerBuff, offset))                           //判断是否正确的指令结构
            {
                //  readerInstrustion.instructionType = ReaderInstructionType.Error_Instruction;
                return null;
            }
            readerInstrustion = new ReaderInstruction();
            for (int i = 0; i < 5; i++)                                     //获取读写器IP
            {
                readerInstrustion.readerIP[i] = readerBuff[offset + i + 4];
            }
            switch (readerBuff[offset + 3])                                          //判断指令类型
            {
                case (byte)0xFF:
                    readerInstrustion.instructionType = ReaderInstructionType.Confirm_Communication;
                    break;
                case (byte)0x03:
                    readerInstrustion.instructionType = ReaderInstructionType.Request_EndCommunication;
                    break;
                case (byte)0xEF:
                    readerInstrustion.instructionType = ReaderInstructionType.Confirm_Edit;
                    break;
                case (byte)0xDF:
                    readerInstrustion.instructionType = ReaderInstructionType.Edit_Succeed;
                    break;
                case (byte)0xDE:
                    readerInstrustion.instructionType = ReaderInstructionType.Edit_Failure;
                    break;
                case (byte)0xFD:
                    readerInstrustion.instructionType = ReaderInstructionType.Confirm_EndCommunication;
                    break;
                default:
                    readerInstrustion.instructionType = ReaderInstructionType.Error_Instruction;
                    break;
            }
            return readerInstrustion;
        }

        /// <summary>
        /// 获得数据包实体类
        /// </summary>
        /// <param name="readerBuff"></param>
        /// <returns></returns>
          public static RFIDDataPackage GetRFIDDataPackage(byte[] readerBuff, int offset)
        {
            RFIDDataPackage rfidDataPackage = null;
            if (!IsDataPackageInstrustion(readerBuff, offset))                  //判断是否正确数据包结构
            {
                return null;
            }
            rfidDataPackage = new RFIDDataPackage();

            for (int i = 0; i < 5; i++)                                     //获取读写器IP
            {
                rfidDataPackage.readerIP[i] = readerBuff[offset + i + 5];
            }
            for (int i = 0; i < 5; i++)                                     //获取节点IP
            {
                rfidDataPackage.nodeIP[i] = readerBuff[offset + i + 13];
            }
            for (int i = 0; i < 6; i++)                                     //获得数据域
            {
                rfidDataPackage.dataField[i] = readerBuff[offset + i + 18];
            }
            return rfidDataPackage;
        }

        /// <summary>
        /// 获得数据包指令
        /// </summary>
        /// <param name="rfidDataPackage"></param>
        /// <returns></returns>
          public static byte[] GetDataPackageInstrustion(RFIDDataPackage rfidDataPackage)
        {
            if (rfidDataPackage == null)
                return null;
            byte[] dataPackageInstrustion = new byte[26];

            for (int i = 0; i < DataPackageLength; i++) //复制模板
            {
                dataPackageInstrustion[i] = DataPackageTemp[i];
            }

            dataPackageInstrustion[3] = 0x03;   //添加参数

            for (int i = 0; i < 5; i++)                                     //复制读写器IP
            {
                dataPackageInstrustion[i + 5] = rfidDataPackage.readerIP[i];
            }
            for (int i = 0; i < 5; i++)                                     //复制节点IP
            {
                dataPackageInstrustion[i + 13] = rfidDataPackage.nodeIP[i];
            }
            for (int i = 0; i < 6; i++)                                     //复制数据域
            {
                dataPackageInstrustion[i + 18] = rfidDataPackage.dataField[i];
            }
            return dataPackageInstrustion;
        }
    }

    class ReaderInstruction
    {
        public ReaderInstructionType instructionType;
        public byte[] readerIP;
        public ReaderInstruction()
        {
            instructionType = ReaderInstructionType.Error_Instruction;
            readerIP = new byte[5];
        }
    }
    class RFIDDataPackage
    {
        public byte[] readerIP;
        public byte[] nodeIP;
        public byte[] dataField;
        public RFIDDataPackage()
        {
            readerIP = new byte[5];
            nodeIP = new byte[5];
            dataField = new byte[6];
        }
    }
    enum ReaderInstructionType
    {
        /// <summary>
        /// 确认通讯
        /// </summary>
        Confirm_Communication,

        /// <summary>
        /// 请求结束通讯
        /// </summary>
        Request_EndCommunication,

        /// <summary>
        /// 确认修改
        /// </summary>
        Confirm_Edit,

        /// <summary>
        /// 确认结束通讯
        /// </summary>
        Confirm_EndCommunication,

        /// <summary>
        /// 修改成功
        /// </summary>
        Edit_Succeed,

        /// <summary>
        /// 修改失败
        /// </summary>
        Edit_Failure,

        /// <summary>
        /// 错误指令
        /// </summary>
        Error_Instruction;

        ///// <summary>
        ///// 数据包
        ///// </summary>
        //DataPackage

    }
    enum PCInstructionType
    {
        /// <summary>
        /// 修改节点信息请求
        /// </summary>
        Request_Communication,

        /// <summary>
        /// 全读请求
        /// </summary>
        Request_Read,


        /// <summary>
        /// 修改节点信息请求
        /// </summary>
        Request_Edit,

        /// <summary>
        /// 通讯结束请求
        /// </summary>
        Request_EndCommunication,


        /// <summary>
        /// 信息读取确认
        /// </summary>
        Confirm_ReadData,

        /// <summary>
        /// 通讯结束确认
        /// </summary>
        Confirm_EndCommunication;
    }
/////////////////////////////////////////////////////////
//add
	class Client{
		public Equipment Equipment;
		public Socket socket;  //需要初始化
		public OutputStream os;
		public String ip = "192.168.1.103";
		public Client(String ip){
			/*try{
				socket = new Socket("192.168.1.103",8000);
			}catch(Exception ex){
				ex.printStackTrace();
			}*/
		}
		public void senddata(Socket sock,byte[] msg){
			try{
				socket = sock;
				
				 os = socket.getOutputStream();
				//System.out.print("111\n");
				os.write(msg);
				System.out.print("Senddate:");
				for(int i = 0; i<msg.length; i++){
					System.out.print((byte)(msg[i] & 0xff) + " ");
				}
				System.out.println();
				//os.close();
				//socket.close();
			}catch(Exception ex){
				ex.printStackTrace();
			}finally{
			//资源回收
			try{
				if(os != null){
					os.close();
				}
				//onlineList.remove(socket);//下线  异常出现的时候的下线情况
			}catch(Exception ee){
				ee.printStackTrace();
			}
		}
		}
	}
