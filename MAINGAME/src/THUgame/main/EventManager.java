package THUgame.main;
import THUgame.datapack.DataPack;
import THUgame.event.EventBase;
import THUgame.event.EventChoice;
import THUgame.event.EventIndom;
import THUgame.event.EventMorningClass;
import THUgame.event.EventNoonClass;
import THUgame.event.EventStateManager;
import THUgame.event.EventHome;
import THUgame.event.EventBackground;
import THUgame.event.EventWelcome;
import THUgame.event.EventOrganization;

public class EventManager extends Thread{
	
    private WindowManager GUI;
    private EventBase pushForward;
	static public DataPack dataPackage;
    public void set(WindowManager GUI){this.GUI=GUI;}
    
    public void run(){
    	while(true){
    		/*********************************		
    		 *     		 * 整体逻辑为：
    		 * 	开始——事件响应——数据包更新——判断是否是新事件——绘图——事件响应……
    		 * 
    		 * 在下面进行分支事件的选择，处理数据包
    		 * 并且重新绘制界面，界面根据数据包内容绘制
    		 * list:
    		 * 	-1.开始后进入的过路事件，什么都不做
    		 * 	0.inDom 在宿舍
    		 * 	1.MorningClass 早上上课事件
    		 *  2.NoonClass 下午上课事件
    		 *  3.Map
    		 *  20001.社工招新->0（非耗时事件）
    		 *  30000.通过选择确定人物模板事件->30002
    		 *  30001.人物基本背景说明及选择提示->30000
    		 *  30002.欢迎界面->0
    		 *  10000.地图界面
    		 * 	
    		 *********************************/
    		/*		START OF YOUR CODE		*/
    		switch(dataPackage.ID) {
    			case -1:
    				pushForward = new EventHome();
    				break;
				case 0:
					pushForward = new EventIndom();
					break;
				case 1:
					pushForward = new EventMorningClass();
					break;	
				case 2:
					pushForward = new EventNoonClass();
					break;
				case 30000:
					pushForward = new EventChoice();
					break;
				case 30001:
					pushForward = new EventBackground();
					break;
				case 30002:
					pushForward = new EventWelcome();
					break;
				case 20001:
					pushForward = new EventOrganization();
    		}
    		/*		END OF YOUR CODE		*/
    		pushForward.actOn(dataPackage);
    		pushForward = new EventStateManager();
    	    pushForward.actOn(dataPackage);
		    GUI.repaint();//每次更新完数据包，用新的数据包重新绘制窗口界面
		    synchronized(this){
		   		try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		   	}
    	}
	}
}
