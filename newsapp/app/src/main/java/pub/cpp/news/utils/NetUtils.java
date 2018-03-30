package pub.cpp.news.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author xzh
 * 1.download google rss news by post
 * 2.download google rss news by get
 *
 */
public class NetUtils {

	public static void getByPost(String url,String path){
		Document doc = null;
		try {
			 doc = Jsoup.connect(url)
					  .data("query", "Java")
					  .userAgent("Mozilla")
					  .cookie("auth", "token")
					  .timeout(10000)
					  .post();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		outFile(doc,path);
	}
   public static void getByGet(String url,String path){
		Document doc = null;
		try {
			 doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		outFile(doc,path);
   }
   public static String getNetStringByGet(String url){

//   	return "[{\"id\":0,\"time\":\"Fri, 08 Dec 2017 06:13:00 GMT\",\"title\":\"���ɹ�24����Ŀ��ѡ��2017ȫ����ѡ������Ŀ�� - ������\",\"category\":\"����/��̨\",\"link\":\"http://news.sina.com.cn/c/2017-12-08/doc-ifypnqvn1474616.shtml\",\"description\":\"�ڽ����ٿ���2017��ȫ������Ͷ���ʴٽ�����ϣ��������ξֻ�ͬ���ҿ������е�12�ҽ��ڻ����ۺϿ�����Ŀ����ȡ������������г�ǰ����ʾ������ʹ������ã���ͬ��ѡ�Ƴ���ȫ��680����ѡ������Ŀ����Ҫ���������������� ...\",\"img\":\"t0.gstatic.com/images?q\\u003dtbn:ANd9GcRJc023FTqF3wtzBsmcYiikyr7kKQeY9L7FPu7CUO-WcIU6f9aH7_dO-z5LX-hJgD700LZz86QF\"},{\"id\":0,\"time\":\"Fri, 08 Dec 2017 01:10:37 GMT\",\"title\":\"����14����ٷ�ȫ�򾯸������⹫�񱣳ָ߶Ⱦ��� - �й�������\",\"category\":\"����/��̨\",\"link\":\"http://www.chinanews.com/gj/2017/12-08/8395428.shtml\",\"description\":\"������12��8�յ����ý��������������ͳ������6������������Ү·����Ϊ��ɫ���׶�����ʼ��������ʹ��Ǩ�Ƶ�����֮����������Ժ7�շ���ȫ�򾯸档 ��������Ժ��ͨ��˵��������������ȫ�����ʩ��߽䱸״̬����Щ��ʩ ...\",\"img\":\"\"},{\"id\":0,\"time\":\"Fri, 08 Dec 2017 03:46:15 GMT\",\"title\":\"����֮��Ͳ�Τ����ƪ��һ�� - �»���\",\"category\":\"����/��̨\",\"link\":\"http://news.xinhuanet.com/2017-12/08/c_1122080013.htm\",\"description\":\"��ͳ�����������������ϲ����޹��ҽ�Ͳ�Τ��������������仯����ִ��Ȩ�ȣ����겢��Ҫִ������������޲��ء��¼ӱ������£�����������ȥ��ͳְ��Ϊ���¼ӱ�ʱ�������Ͼ�š� �����£������賿��ʱ���ң���Ͳ�Τ�׶��������������� ...\",\"img\":\"t1.gstatic.com/images?q\\u003dtbn:ANd9GcSJMR-EVQM3n2n8NqN-0vOXEZPISbddcHqC-zsG6gRM24jCuSo0DuecK0gnTb4vZGCuF-MBZXUO\"},{\"id\":0,\"time\":\"Fri, 08 Dec 2017 04:27:25 GMT\",\"title\":\"�Ƿ���ռ�С�ͷĿ��֮���������������Ѻ����ʦ���飺�������18.... - ������\",\"category\":\"����/��̨\",\"link\":\"http://news.sina.com.cn/c/2017-12-08/doc-ifypnyqi1966363.shtml\",\"description\":\"... ���������ۺϱ�������۸ߵȷ�Ժԭ��12��7������ԷǷ���ռ�С��ڼ����ӡ��������ӷ�����Ļ�֮��һ��16�����̡������ݸ�ý��������֮����ʦ��Ӧ��7�ճ�ͥʱ�򷨹١����顱���Ʊ��浱ʱֻ��18�꣬�����ѱ����̰��꣬ϣ ...\",\"img\":\"t1.gstatic.com/images?q\\u003dtbn:ANd9GcTs-YhvT8KROWVkIPSlA6SckTqD4VZc_T3WixWIIdqYFeNLTOrwRxASVsPopFv0a2qylDhI-CTa\"},{\"id\":0,\"time\":\"Fri, 08 Dec 2017 02:28:02 GMT\",\"title\":\"ԣ����ʶ�ս����¼�������ɽ���27.5����Ԫ - ŦԼʱ��������\",\"category\":\"����/��̨\",\"link\":\"https://cn.nytimes.com/asia-pacific/20171208/japan-emperor-hirohito-memoirs/\",\"description\":\"���������������ձ�ս��ʱ�ڵ����ԣ��(Hirohito)�Ļ���¼�ָ���ŦԼ�ĵ�27.5����Ԫ��Լ��180��Ԫ����ң���ԣ���ڻ���¼�н������ձ��μӡ���ս����ԭ�� ��ݻ���¼���ձ���һ����ͥ��Ա��Ǧ�ʺͺ�ɫīˮ��д���ɣ��� ...\",\"img\":\"t2.gstatic.com/images?q\\u003dtbn:ANd9GcS1lpbqaMR2PAr-h0B7FEDBvi6u7BBxTprHnj2KJmGqstBvd8UU7j5raCKfP_YOn8loU3ENC_eR\"},{\"id\":0,\"time\":\"Fri, 08 Dec 2017 04:55:57 GMT\",\"title\":\"������ī������У԰ǹ���£������� - �»���\",\"category\":\"����/��̨\",\"link\":\"http://news.xinhuanet.com/2017-12/08/c_1122080183.htm\",\"description\":\"�»�����˹�أ����£��յ磨���߸�·����ΰ�������ϲ���ī������һ�����У��շ���ǹ���¼�����ɰ���ǹ�����ڵģ��������� ����ǹ���ĸ���λ����ī�����ݰ���̨���У���������׸��������˻�Լ������������ؾ���֤ʵ��ǹ���� ...\",\"img\":\"t0.gstatic.com/images?q\\u003dtbn:ANd9GcTNsWHOHPVu1GP044dDbnLwEMkl1_STcb5jwIxRzsGxVVByXq240snaoR0mfoxALj_ALay6oWaI\"},{\"id\":0,\"time\":\"Fri, 08 Dec 2017 07:26:15 GMT\",\"title\":\"̫ƽ��ս������76������������۾��е�����ʽ - ������\",\"category\":\"����/��̨\",\"link\":\"http://news.sina.com.cn/w/2017-12-08/doc-ifypnyqi2040702.shtml\",\"description\":\"... ������������ ʵϰ���� ���ڷɡ����ձ����������š�12��8�ձ���������ʱ��12��7�����ϣ���������������۾���׷���ᣬ������76��ǰ��������¼����������������ٱ�����Ϯ�����Ҵ�������20���������ϱ�Ҳ�μ�����ʽ�� ׷ ...\",\"img\":\"t2.gstatic.com/images?q\\u003dtbn:ANd9GcSJVngWkUoqu7je1oQuH720SshcP4cTO8dhEDwgW-FK4EWNCRyU-tVDz3jnW2gjf5scS0XdoNfn\"},{\"id\":0,\"time\":\"Fri, 08 Dec 2017 05:31:32 GMT\",\"title\":\"һ���ڵ���λ��һ���͵�����Ա����ɧ�ų��Ŵ�ְ - ���ľ�γ\",\"category\":\"����/��̨\",\"link\":\"http://huaxia.com/xw/gjxw/2017/12/5566514.html\",\"description\":\"������12��8�յ����ý��������������Ժ����ίԱ����¶����ʼ��Կ��ܵ���ɧ����Ϊ���е���֮������ɣ���ݵĹ��͵�������Ա������˹������ְ�� ������˹��7�����Ϲ����������кͳ����������Ĺ�����Ա�о�������� ...\",\"img\":\"t0.gstatic.com/images?q\\u003dtbn:ANd9GcR_Sf3uVa9eQq9b8rsvrgCTAcbOWjaLGAGxkOrpL0ge51w3o3aKnFx0q7rU0dkGewO2eR76YVuI\"},{\"id\":0,\"time\":\"Fri, 08 Dec 2017 07:26:15 GMT\",\"title\":\"�쳷������ĩ��ɽ��ʧ�أ����ݸ漱�� - ������\",\"category\":\"����/��̨\",\"link\":\"http://news.sina.com.cn/o/2017-12-08/doc-ifypnyqi2038881.shtml\",\"description\":\"����ʱ��12��5�գ�����λ����ɼ�����������ͼ���أ�Ventura County���ٴ�ͻ��ɽ�� ���Ӳ��ҵ��ǣ���ǿ��Ӱ�죬����Ѹ�����ӣ�����45,000ӢĶ���Ѿ����յ�����ɼ������ˡ� ����150�������ﱻ�ճɷ��档 ����26����ͣ�� ...\",\"img\":\"t1.gstatic.com/images?q\\u003dtbn:ANd9GcThffK6Mid43XKbE6Wa0YH_PNz78bkngdYZhGkrlW5agYS8EyThJwnzKFrgt3gCN3TD_6mpjFcj\"},{\"id\":0,\"time\":\"Fri, 08 Dec 2017 07:29:34 GMT\",\"title\":\"ŷ����Ӣ�������ŷЭ��Ϊó��̸����ƽ��· - ������\",\"category\":\"����/��̨\",\"link\":\"http://finance.sina.com.cn/7x24/2017-12-08/doc-ifypnqvn1490824.shtml\",\"description\":\"��������Ѷ����ʱ��8�������ۺ���Ϣ����ý������ŷ����Ӣ�������ŷЭ�飬Ϊó��̸����ƽ��·���������۽�ָ����ҵ����е���ŷ���ǰ;�ͷ��� CNN��Ϣ�ƣ�Ӣ����������ɯ-÷8����ŷ��ίԱ����ϯ�ݿ˻��档����һ�� ...\",\"img\":\"t2.gstatic.com/images?q\\u003dtbn:ANd9GcRKtFRIrZSajOn9Y8798yHdXYDSdpajSyCAZa6O4QAzDkCOlRj2KnuzcOX8Ez0q_8H8RcMybe5a\"}]\n";
		Document doc = null;
		try {
			 doc = Jsoup.connect(url).get();
			return doc.getElementsByTag("body").get(0).text();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return  null;
		}
   }
	public static String getNetStringByPost(String url ){
		Document doc = null;
		try {
			 doc = Jsoup.connect(url)
					  .data("query", "Java")
					  .userAgent("Mozilla")
					  .cookie("auth", "token")
					  .timeout(10000)
					  .post();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc.getAllElements().toString();
	}
   private static void outFile(Document doc,String path){
	   	Elements outString=doc.getAllElements();
		String mFileName=doc.getElementsByTag("title").get(0).text();
		System.out.println("mFileName:"+mFileName);
		File mFile=new File(path+File.separator+mFileName+".html");
		System.out.println("mFile:"+mFile.getPath());
		FileOutputStream out = null;
		try {
			out=new FileOutputStream(mFile);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			out.write(outString.toString().getBytes("utf-8"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
   }
   
   public static final void main(String[] args){
	   String url="http://cpp.pub/webnews/news?type=1";
	   String str=getNetStringByGet(url);
	   System.out.println(str);
   }
   
   
}
