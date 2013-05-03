
package Database;

public class DBHelper {
public static final String LESSON_TABLE_NAME = "lessonTable";
public static final String RECORD_TABLE_NAME = "recordTable";

//Table column names
public static final String ID = "id";
public static final String NAME = "name";
public static final String LESSON_NAME = "lessonName";
public static final String CORRECT_CHOICE = "correctChoice";
public static final String ACTUAL_CHOICE = "actualChoice";
public static final String REWARD = "reward";
public static final String AUDIO_REWARD = "audioReward";
public static final String VIDEO_REWARD = "videoReward";
public static final String RESPONSE_TIME = "responseTime";
public static final String IMAGE_ONE_PATH = "imageOnePath";
public static final String IMAGE_TWO_PATH = "imageTwoPath";
public static final String IMAGE_THREE_PATH = "imageThreePath";
public static final String IMAGE_FOUR_PATH = "imageFourPath";
public static final String COMMENTS = "comments";

//SQL Statements
public static final String RECORD_TABLE_CREATE =
  "CREATE TABLE IF NOT EXISTS " + RECORD_TABLE_NAME + " ("
  + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " , "
  + LESSON_NAME + " , " + CORRECT_CHOICE + " , "  + REWARD + " , " +
  AUDIO_REWARD + " , " + VIDEO_REWARD + " , " + IMAGE_ONE_PATH + " , " +
  IMAGE_TWO_PATH + " , " + IMAGE_THREE_PATH + " , " +
  IMAGE_FOUR_PATH + " , " + COMMENTS + " , " + RESPONSE_TIME + " ," + ACTUAL_CHOICE + " );";

public static final String LESSON_TABLE_CREATE = 
  "CREATE TABLE IF NOT EXISTS " + LESSON_TABLE_NAME + " ("
  + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " , "
  + LESSON_NAME + " UNIQUE, " + CORRECT_CHOICE + " , " +
  REWARD + " , " + AUDIO_REWARD + " , " + VIDEO_REWARD +
  " , " + IMAGE_ONE_PATH + " , " + IMAGE_TWO_PATH + " , " +
  IMAGE_THREE_PATH + " , " + IMAGE_FOUR_PATH + " , " + COMMENTS +
  " );";

public DBHelper() {

}


}
