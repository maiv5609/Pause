/**
 * Created by Victor on 2/25/2018.
 */

/*
    Class to be used to create dog and get information for breed from xml file
 */

//Default
public class Dogs {
    String name;
    String breedName;
    String imagePath; //Might need to change this?
    double growthTime;

    Dogs(String name, String breedName, String imagePath, double growthTime){
        this.name = name;
        this.breedName = breedName;
        this.imagePath = imagePath;
        this.growthTime = growthTime;
    }
}


