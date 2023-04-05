package apiPackage;



public class Pojo {

    private String name;
    private String url;
    private String weight;

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public Pojo(){

    }
    public void setName(String name){
        this.name = name;
    }
    public void setUrl(String url){
        this.url = url;
    }
    public void setWeight(String weight){
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
    public String getWeight(){
        return weight;
    }


}
