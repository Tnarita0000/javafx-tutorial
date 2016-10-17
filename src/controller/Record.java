package sample;
import javafx.collections.ObservableList;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import java.util.Map;
public class Record {

  private ObjectProperty<Map<String, String>> record =
    new SimpleObjectProperty<Map<String, String>>();

  public Record setData(Map<String,String> dataMap){
    record.set(dataMap);
    return this;
  }

  public String getData(String key){
    return record.get().get(key);
  }
}
