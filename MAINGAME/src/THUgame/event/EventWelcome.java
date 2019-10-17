package THUgame.event;

import THUgame.datapack.DataPack;

/*
 * 正是游戏前的欢迎界面
 * 
 * ---DIALOG---
 * update:20191018
 * via：黄天翼
 * 
 * */

public class EventWelcome extends EventBase{
	public void actOn(DataPack oldDataPack) {
		oldDataPack.count = oldDataPack.count + 1;
		if(oldDataPack.count == 3) {
			oldDataPack.eventFinished = true;
		}
	}
}