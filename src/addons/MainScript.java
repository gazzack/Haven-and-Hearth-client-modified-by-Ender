package addons;

import haven.*;

public class MainScript{
	public static void flaskScript(){
		HavenUtil util = UI.instance.m_util;
		if(!util.runFlaskRunning){
			RunFlaskScript rfs = new RunFlaskScript(util);
			
			if(rfs != null){
				util.runFlaskRunning = true;
				rfs.start();
			}
			
		}
	}
	
	public static void multiTool(){
		int modify = UI.instance.modflags();
		Gob object = UI.instance.mainview.gobAtMouse;
		if(object == null) return;
		int type = ObjectType(object);
		
		if(type == 0){
			int range = 1;
			if(modify == 4) range = 1000;
			
			cleanupItems(range, object);
		}else if(type == 1){
			if(modify == 1)
				object.animalTag = false;
			else if(modify == 4)
				object.animalTag = true;
		}
	}
	
	private static int ObjectType(Gob object){
		String name = object.resname();
		
		if(name.contains("/items/"))
			return 0;
		if(name.equals("gfx/kritter/hen/cdv") || name.equals("gfx/kritter/hen/cock-dead") || name.equals("gfx/kritter/hare/cdv"))
			return 0;
		if(name.equals("gfx/kritter/sheep/s") || name.equals("gfx/kritter/pig/s") || name.equals("gfx/kritter/cow/s"))
			return 1;
		
		return -1;
	}
	
	public static void cleanupItems(int areaSize, Gob object){
		HavenUtil util = UI.instance.m_util;
		
		if(!util.cleanupRunning && object != null){
			Coord pickupCoord = UI.instance.mainview.mousepos;
			Coord c1 = pickupCoord.add(-11*areaSize,-11*areaSize);
			Coord c2 = pickupCoord.sub(-11*areaSize,-11*areaSize);
			
			CleanupScript cs = new CleanupScript(util, c1, c2, object, new Coord(0,0) );
			
			if(cs != null){
				util.stop = false;
				util.cleanupRunning = true;
				cs.start();
			}
		}
	}
	
	public static void autoLand(){
		HavenUtil util = UI.instance.m_util;
		
		if(!util.landscapeRunning){
			AutoLandscape al = new AutoLandscape(util, util.m_pos1, util.m_pos2, util.m_Type);
			
			if(al != null){
				util.stop = false;
				util.landscapeRunning = true;
				al.start();
			}
		}
	}
	
	public static void autoFeast(){
		HavenUtil util = UI.instance.m_util;
		
		if(!util.feastRunning){
			AutoFeast af = new AutoFeast(util, util.m_Type);
			
			if(af != null){
				util.stop = false;
				util.feastRunning = true;
				af.start();
			}
		}
	}
	
	public static void seedbagScript(boolean transfer){
		HavenUtil util = UI.instance.m_util;
		
		if(!util.seedbagRunning){
			
			SeedbagScript sbs = new SeedbagScript(util, transfer);
			
			if(sbs != null){
				util.stop = false;
				util.seedbagRunning = true;
				sbs.start();
			}
		}
	}
}