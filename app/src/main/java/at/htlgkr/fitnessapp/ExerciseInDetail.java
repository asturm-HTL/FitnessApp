package at.htlgkr.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

public class ExerciseInDetail extends AppCompatActivity
{

    public static String exerciseStringToSplit;
    public String exerciseExplanationInclTips;
    public String exerciseExplanation;
    public String exerciseCommentsAndTips;
    public String exerciseTarget;
    public String exerciseName;
    public String exerciseImgLink;

    public PhotoView exerciseImageView;
    public TextView tvExerciseName;
    public TextView tvTargetMuscle;
    public TextView tvTargetMuscleToEdit;
    public TextView tvExplanation;
    public TextView tvExplanationToEdit;
    public TextView tvCommentsAndTips;
    public TextView tvCommentsAndTipsToEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_in_detail);

        String[] split1 = exerciseStringToSplit.split(",\"");

        exerciseImageView = (PhotoView) findViewById(R.id.exerciseImageView);
        tvExerciseName = findViewById(R.id.tvExerciseName);
        tvTargetMuscle = findViewById(R.id.tvTargetMuscle);
        tvTargetMuscleToEdit = findViewById(R.id.tvTargetMuscleToEdit);
        tvExplanation = findViewById(R.id.tvExplanation);
        tvExplanationToEdit = findViewById(R.id.tvExplanationToEdit);
        tvCommentsAndTips = findViewById(R.id.commentsAndTips);
        tvCommentsAndTipsToEdit = findViewById(R.id.commentsAndTipsToEdit);

        for(int i = 0; i < split1.length; i++)
        {

            if(i == 1)
            {
                String[] splitname = split1[1].split(":");
                String exercisename = splitname[1].replace("\"", "").toString();
                tvExerciseName.setText(exercisename);
            }
            if(i == 3)
            {
                String[] splittarget = split1[3].split(":");
                String targetname = splittarget[1].replace("\"", "").toString();
                tvTargetMuscleToEdit.setText(targetname);
                if(targetname.contains(","))
                {
                    tvTargetMuscle.setText("Target Muscles:");
                }
            }
            if(i == 4)
            {
                String[] splitexplanationandtips = split1[4].split(";Comments and tips:");
                String explanationString = splitexplanationandtips[0].replace("explanation\":\"", "");
                String commentsandtipsString = splitexplanationandtips[1];

                String[] explanationsplit1 = explanationString.split(":");
                String[] splitExecution = explanationsplit1[1].split(";Execution");
                String explanationendString = explanationsplit1[0]+ ":\n-"+splitExecution[0].replace(";", "\n-") + "\n\nExecution:\n-" + explanationsplit1[2].replace(";", "\n-");

                tvExplanationToEdit.setText(explanationendString);
                tvCommentsAndTipsToEdit.setText("-" + commentsandtipsString.replace(";", "\n-"));
            }
            if(i == 5)
            {
                String[] splitimglink = split1[5].split(":");
                String imlinkString = splitimglink[1].replace("\"", "").replace("}", "");
                Picasso.get().load("http://www.fitnesscenter-mitter.at/img/FitnessApp/" + imlinkString + ".png").into(exerciseImageView);
               // exerciseImageView.setImageResource(R.drawable.abductorsanatomy);


            }
        }
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();

            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

    }
}
