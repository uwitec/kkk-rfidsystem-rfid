

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author Roy
 * Description ��װ���е�����
 *
 */
public class Information implements Serializable{

	/**
	 * 
	 */
	private int type; //��ǰ���������
	public Object content;//��ŵĶ��� 
	private Date send_date;//����ʱ��/��Ӧʱ��
	private Object source;//��Դ��������Դ����Ϣ
	public static final int CLIENTLOGIN = 1;
	//������  ��������  ����  
	public Information(int type, Object content) {
		this.type = type;
		this.content = content;
	}
	//������  ��������  ����  ��Դ
	public Information(int type,Object content,Object source) {
		this.type = type;
		this.content = content;
		this.source=source;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Object getContent() {
		return content;
	}
	public void setContent(Object content) {
		this.content = content;
	}
	public Date getSend_date() {
		return send_date;
	}
	public void setSend_date(Date sendDate) {
		send_date = sendDate;
	}
	public Object getSource() {
		return source;
	}
	public void setSource(Object source) {
		this.source = source;
	}
	@Override
	public String toString() {
		return "Information [content=" + content + ", send_date=" + send_date
				+ ", source=" + source + ", type=" + type + "]";
	}
	

}
