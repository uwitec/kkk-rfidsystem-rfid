

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author Roy
 * Description 封装所有的请求
 *
 */
public class Information implements Serializable{

	/**
	 * 
	 */
	private int type; //当前对象的类型
	public Object content;//存放的对象 
	private Date send_date;//请求时间/响应时间
	private Object source;//来源，可以来源的信息
	public static final int CLIENTLOGIN = 1;
	//构造器  请求类型  内容  
	public Information(int type, Object content) {
		this.type = type;
		this.content = content;
	}
	//构造器  请求类型  内容  来源
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
