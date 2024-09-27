package com.example.MyComicsApp;

import static android.app.PendingIntent.getActivity;

import static com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions.RESULT_FORMAT_JPEG;
import static com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions.SCANNER_MODE_BASE;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts.StartIntentSenderForResult;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;

import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions;
import com.google.mlkit.vision.documentscanner.GmsDocumentScanning;
import com.google.mlkit.vision.documentscanner.GmsDocumentScanningResult;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddComicActivity extends AppCompatActivity {

    private Comic.Publisher publisherInput;
    private String indiePublisherInput;
    private String seriesTitleInput;
    private int issueNumInput;
    private String writerInput;
    private String artistInput;
    private Date publishedDateInput;
    private Date purchasedDateInput;
    private Float purchasedPriceInput;
    private CheckBox marvelCB;
    private CheckBox dcCB;
    private CheckBox indieCB;
    private EditText indiePublisherField;
    private EditText seriesTitleField;
    private EditText issueNumField;
    private EditText writerField;
    private EditText artistField;
    private EditText purchasedPriceField;
    private ImageView scanToAdd;
    private Button scanButton;
    private Button addToLibraryButton;
    private Button returnToLibraryButton;
    private final Toaster toaster = new Toaster();
    private boolean optionsSelected = false;
    private boolean scanAdded = false;
    private ActivityResultLauncher<IntentSenderRequest> scannerLauncher;
    private byte[] imageAsBytesInput;
    private ComicViewModel comicViewModel;
    private NumberPicker publishedMonthPicker;
    private NumberPicker publishedDayPicker;
    private NumberPicker publishedYearPicker;
    private NumberPicker purchasedMonthPicker;
    private NumberPicker purchasedDayPicker;
    private NumberPicker purchasedYearPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comic);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        marvelCB = (CheckBox) findViewById(R.id.marvel_checkbox);
        dcCB = (CheckBox) findViewById(R.id.dc_checkbox);
        indieCB = (CheckBox) findViewById(R.id.indie_checkbox);
        indiePublisherField = (EditText) findViewById(R.id.indie_publisher_field);
        seriesTitleField = (EditText) findViewById(R.id.series_title_field);
        issueNumField = (EditText) findViewById(R.id.issue_number_field);
        scanToAdd = (ImageView) findViewById(R.id.scan_to_add);
        writerField = (EditText) findViewById(R.id.writer_field);
        artistField = (EditText) findViewById(R.id.artist_field);

        scanButton = (Button) findViewById(R.id.scan_button);
        purchasedPriceField = (EditText) findViewById(R.id.purchased_price_field);
        addToLibraryButton = (Button) findViewById(R.id.add_to_library_button);
        returnToLibraryButton = (Button) findViewById(R.id.return_to_library_button);

        publishedMonthPicker = (NumberPicker) findViewById(R.id.published_month_picker);
        publishedDayPicker = (NumberPicker) findViewById(R.id.published_day_picker);
        publishedYearPicker = (NumberPicker) findViewById(R.id.published_year_picker);
        purchasedMonthPicker = (NumberPicker) findViewById(R.id.purchased_month_picker);
        purchasedDayPicker = (NumberPicker) findViewById(R.id.purchased_day_picker);
        purchasedYearPicker = (NumberPicker) findViewById(R.id.purchased_year_picker);

        Date today = new Date();
        Long now = today.getTime();
        int currentYear  = Integer.valueOf( (String) DateFormat.format("yyyy", now));
        int currentMonth = Integer.valueOf((String) DateFormat.format("MM", now));
        int currentDay = Integer.valueOf((String) DateFormat.format("dd", now));

        publishedMonthPicker.setMinValue(1);
        purchasedMonthPicker.setMaxValue(1);
        publishedMonthPicker.setMaxValue(12);
        purchasedMonthPicker.setMaxValue(12);
        publishedMonthPicker.setValue(currentMonth);
        purchasedMonthPicker.setValue(currentMonth);

        publishedDayPicker.setMinValue(1);
        purchasedDayPicker.setMinValue(1);
        publishedDayPicker.setMaxValue(31);
        purchasedDayPicker.setMaxValue(31);
        publishedDayPicker.setValue(currentDay);
        purchasedDayPicker.setValue(currentDay);

        publishedYearPicker.setMinValue(1899);
        purchasedYearPicker.setMinValue(1899);
        publishedYearPicker.setMaxValue(currentYear);
        purchasedYearPicker.setMaxValue(currentYear);
        publishedYearPicker.setValue(currentYear);
        purchasedYearPicker.setValue(currentYear);

        comicViewModel = LibraryActivity.comicViewModel;

        scannerLauncher = registerForActivityResult(new StartIntentSenderForResult(), this::handleActivityResult);

        addToLibraryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkRequiredFields()){
                    checkOptionalFields();
                }
            }
        });

        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanNewImage();
            }
        });

        returnToLibraryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnToLibrary();
            }
        });

        marvelCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    dcCB.setChecked(false);
                    indieCB.setChecked(false);
                }
            }
        });

        dcCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    marvelCB.setChecked(false);
                    indieCB.setChecked(false);
                }
            }
        });

        indieCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    dcCB.setChecked(false);
                    marvelCB.setChecked(false);
                    indiePublisherField.setVisibility(View.VISIBLE);
                }
                else{
                    indiePublisherField.setVisibility(View.INVISIBLE);
                }
            }
        });

    }

    private void addToLibrary() {
        Comic comic = new Comic(publisherInput, indiePublisherInput,
                imageAsBytesInput,
                seriesTitleInput, issueNumInput,
                writerInput, artistInput);
        if (isInLibrary(comic)) {
            toaster.msg(AddComicActivity.this, "Comic already in library!");
        } else {
            clearFields();
            comicViewModel.insertComic(comic);
            toaster.msg(AddComicActivity.this, "Comic added to library!");
        }
    }

    private void handleActivityResult(ActivityResult activityResult){

        int resultCode = activityResult.getResultCode();
        GmsDocumentScanningResult result =
                GmsDocumentScanningResult.fromActivityResultIntent(activityResult.getData());
        if (resultCode == Activity.RESULT_OK && result != null) {
            if (!result.getPages().isEmpty()) {
                toaster.msg(AddComicActivity.this, "Scan successful!" );
                try {
                    Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(
                            result.getPages().get(0).getImageUri()));
                    scanToAdd.setImageBitmap(bitmap);
                    int width = bitmap.getWidth();
                    int height = bitmap.getHeight();
                    Bitmap scaledBitMap = Bitmap.createScaledBitmap(bitmap, width/15 , height/15, false);
                    byte[] imageData = getBitmapAsByteArray(scaledBitMap);
                    imageAsBytesInput = imageData;
                    scanAdded = true;
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }

            }

        } else if (resultCode == Activity.RESULT_CANCELED) {
            toaster.msg(AddComicActivity.this, "Scan was cancelled!");
        } else {
            toaster.msg(AddComicActivity.this, "ERROR: Scanner failure");
        }

    }

    private byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }

    private void checkOptionalFields() {
        List<EditText> optionalFields = new ArrayList<>();
      //  optionalFields.add(publishedDateField);      //0
      //  optionalFields.add(purchasedDateField);      //1
        optionalFields.add(purchasedPriceField);     //2

        List<Integer> emptyFields = new ArrayList<>();
        for(int i = 0; i < optionalFields.size(); i++){
            if(optionalFields.get(i).getText().toString().trim().matches("")){
                emptyFields.add(i);
            }
        }

        if(!scanAdded){
            emptyFields.add(3);
        }

        if(emptyFields.isEmpty()){ addToLibrary(); }
        else{ checkWithUser(); }
    }

    private void checkWithUser() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AddComicActivity.this);
        builder.setMessage("Some optional fields weren't filled in," +
                            " do you want to add comic to library anyway?");
        // Add the buttons.
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                addToLibrary();
            }
        });
        builder.setNegativeButton("No, go back!", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void clearFields() {
        marvelCB.setChecked(false);
        dcCB.setChecked(false);
        indieCB.setChecked(false);
        indiePublisherField.setText("");
        seriesTitleField.setText("");
        issueNumField.setText("");
        writerField.setText("");
        artistField.setText("");
        purchasedPriceField.setText("");
        publisherInput = null;
        scanToAdd.setImageResource(R.drawable.add_photo);
        optionsSelected = false;
    }

    private boolean isInLibrary(Comic comic) {
        List<Comic> comics = (List<Comic>) LibraryActivity.comicsInLibrary;
        if(comics == null){
            return false;
        }
        for (int i = 0; i < comics.size(); i++){
            if(comics.get(i).matches(comic)){
                return true;
            }
        }
        return false;
    }
    private void returnToLibrary() {
        Intent libraryIntent = new Intent(AddComicActivity.this, LibraryActivity.class);
        startActivity(libraryIntent);
    }
    private boolean checkRequiredFields() {
        List<EditText> requiredFields = new ArrayList<>();
        requiredFields.add(seriesTitleField);   //0
        requiredFields.add(issueNumField);      //1
        requiredFields.add(writerField);        //2
        requiredFields.add(artistField);        //3

        List<Integer> emptyFields = new ArrayList<>();
        for(int i = 0; i < requiredFields.size(); i++){
            if(requiredFields.get(i).getText().toString().trim().matches("")){
                emptyFields.add(i);
            }
        }
        //emptyFields[4] = publisherInput
        if (!marvelCB.isChecked() && !dcCB.isChecked() && !indieCB.isChecked()){emptyFields.add(4);}
        //emptyFields[5] = indiePublisherField
        if(indieCB.isChecked()){
            if(indiePublisherField.getText().toString().trim().matches("")){
                emptyFields.add(5);
            }
            else{
                publisherInput = Comic.Publisher.INDIE;
                indiePublisherInput = indiePublisherField.getText().toString();
            }
        }
        if(emptyFields.size() == 0){
            seriesTitleInput = seriesTitleField.getText().toString().trim();
            issueNumInput = Integer.valueOf(issueNumField.getText().toString().trim());
            writerInput = writerField.getText().toString().toString().trim();
            artistInput = artistField.getText().toString().toString().trim();
            if(marvelCB.isChecked()){
                publisherInput = Comic.Publisher.MARVEL;
            }
            else if(dcCB.isChecked()){
                publisherInput = Comic.Publisher.DC;
            }
            return true;
        }
        else if(emptyFields.size() > 1){toaster.multipleFields(AddComicActivity.this);}
        else {
            switch (emptyFields.get(0)){
                case 0:
                    toaster.requiredField(AddComicActivity.this,"Series title");
                    break;
                case 1:
                    toaster.requiredField(AddComicActivity.this, "Issue number");
                    break;
                case 2:
                    toaster.requiredField(AddComicActivity.this, "Writer");
                    break;
                case 3:
                    toaster.requiredField(AddComicActivity.this, "Artist");
                    break;
                case 4:
                    toaster.requiredField(AddComicActivity.this, "Publisher");
                    break;
                case 5:
                    toaster.msg(AddComicActivity.this,
                    "Publisher name is required when Indie publisher is selected");
            }
        }
        return false;
    }
    private void scanNewImage() {

        GmsDocumentScannerOptions options = new GmsDocumentScannerOptions.Builder()
                .setGalleryImportAllowed(true)
                .setPageLimit(1)
                .setResultFormats(RESULT_FORMAT_JPEG)
                .setScannerMode(SCANNER_MODE_BASE)
                .build();

        GmsDocumentScanning.getClient(options)
            .getStartScanIntent(this)
            .addOnSuccessListener(
                    intentSender ->
                            scannerLauncher.launch(new IntentSenderRequest.Builder(intentSender).build()))
            .addOnFailureListener(
                    e -> toaster.msg(AddComicActivity.this, "error") );
    }
    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.getItem(1).setVisible(false); //1 is the add comic icon set to invisible when on the add comic screen
        return true;
    }
    @Override
    public boolean  onOptionsItemSelected(MenuItem item) {
        CharSequence title = item.getTitle();
        if (title.equals("Library")) {
            Intent libraryIntent = new Intent(AddComicActivity.this, LibraryActivity.class);
            startActivity(libraryIntent);
        } else if (title.equals("Comic Shop Locator")) {
            Intent comicShopLocatorIntent = new Intent(AddComicActivity.this, ComicShopLocatorActivity.class);
            startActivity(comicShopLocatorIntent);
        }
        return true;
    }

}