package at.htlgkr.fitnessapp;

public class MuscleAnatomy
{

    private String musclename;
    private int anatomyImage;

    public MuscleAnatomy(String musclename, int anatomyImage)
    {
        this.musclename = musclename;
        this.anatomyImage = anatomyImage;
    }

    public String getMuscleName()
    {
        return musclename;
    }

    public int getAnatomyImage()
    {
        return anatomyImage;
    }

}
