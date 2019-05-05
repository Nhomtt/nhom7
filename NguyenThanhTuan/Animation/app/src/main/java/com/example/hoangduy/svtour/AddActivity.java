package com.example.hoangduy.svtour;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;


import com.example.hoangduy.svtour.database.DatabaseHelper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class AddActivity extends AppCompatActivity {

    private DatabaseHelper DatabaseHelper;
    private EditText edtTourName, note_name_dialog, note_description_dialog;
    private ImageView ivImage;
    private VideoView vvVideo;
    private Dialog dialog;
    private TextView tvNote;
    private ImageButton btnThemAnh, btnThemVideo, btnThemNote;
    private Button btnHuy, btnLuu, cancel_dialog, save_note_dialog;
    Uri videoUri;

    private static final int REQUEST_ID_READ_WRITE_PERMISSION = 99;
    private static final int REQUEST_ID_IMAGE_CAPTURE = 100;
    private static final int REQUEST_ID_VIDEO_CAPTURE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);

        // Lấy các đối tượng trên AddActivity layout
        btnThemAnh = (ImageButton) findViewById(R.id.btnAddImage);
        btnThemVideo = (ImageButton) findViewById(R.id.btnAddVideo);
        btnThemNote = (ImageButton) findViewById(R.id.btnAddNote);
        btnHuy = (Button) findViewById(R.id.btnHuy);
        btnLuu = (Button) findViewById(R.id.btnLuu);
        edtTourName = (EditText) findViewById(R.id.edtTourName);

        ivImage = (ImageView) findViewById(R.id.ivImage);
        vvVideo = (VideoView) findViewById(R.id.vvVideo);
        tvNote = (TextView) findViewById(R.id.tvNote);

        // Khởi tạo dialog thêm ghi chú
        dialog = new Dialog(AddActivity.this);
        dialog.setTitle("Thêm ghi chú");
        dialog.setContentView(R.layout.dialog_add_note);

        // Lấy id các đối tượng trên dialog
        cancel_dialog = (Button) dialog.findViewById(R.id.cancel_dialog);
        save_note_dialog = (Button) dialog.findViewById(R.id.save_note_dialog);
        note_name_dialog = (EditText) dialog.findViewById(R.id.note_name_dialog);
        note_description_dialog = (EditText) dialog.findViewById(R.id.note_description_dialog);

        // Nhấn vào thêm ảnh
        btnThemAnh.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                captureImage();
            }
        });

        // Nhấn vào thêm video
        btnThemVideo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                askPermissionAndCaptureVideo();
            }
        });


        // Nhấn vào thêm node
        btnThemNote.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                note_description_dialog.setText("");
                note_name_dialog.setText("");
                dialog.show();
            }
        });

        // Hủy thêm tour trở về màng hình home
        btnHuy.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(AddActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        // Hủy dialog thêm node
        cancel_dialog.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        // Lưu dialog add note
        save_note_dialog.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                btnThemNote.setVisibility(View.GONE);
                dialog.dismiss();
                tvNote.setVisibility(View.VISIBLE);
                tvNote.setText("Ghi chú: "+note_name_dialog.getText());
            }
        });

        // KHI NHẤN VÀO NÚT LƯU TOUR ----------------------------------------------------
        btnLuu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Lấy ngày hiện tại trên hệ thống
                Date today = new Date(System.currentTimeMillis());
                SimpleDateFormat timeFormat = new SimpleDateFormat("dd/MM/yyyy");
                String date = timeFormat.format(today.getTime());

                String tour_name = edtTourName.getText().toString();

                if(tour_name.equals("")) {
                    Toast.makeText(AddActivity.this,"Hãy nhập tên chuyến đi của bạn!",Toast.LENGTH_LONG).show();
                    return;
                }
                else
                {
                    DatabaseHelper = new DatabaseHelper(AddActivity.this);

                    try {
                        DatabaseHelper.insertTour(tour_name, date, imageViewToByte(ivImage));
                    } catch (Exception e) {
                        DatabaseHelper.insertTourNoPic(tour_name, date);
                    }

                    //Thêm note
                    int tour_id = DatabaseHelper.getTourJustInsertId();
                    String note_title = note_name_dialog.getText().toString();
                    String note_description = note_description_dialog.getText().toString();

                    if(note_description.length() > 0) {
                        if (note_title.equals("")) {
                            note_title = "ghi chú " + date;
                            DatabaseHelper.insertNote(tour_id, note_title, note_description);
                        } else {
                            DatabaseHelper.insertNote(tour_id, note_title, note_description);
                        }
                    }
                    //Lưu image nếu có ảnh được chụp
                    try {
                            DatabaseHelper.insertImage(tour_id, imageViewToByte(ivImage));
                    } catch (Exception e) {
                            e.printStackTrace();
                    }

                    //Lưu video url nếu có video được quay
                    try{
                            DatabaseHelper.insertVideo(tour_id,videoUri.toString());
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    //Sau khi thêm xong chuyển về màng hình home
                    Intent intent = new Intent(AddActivity.this, HomeActivity.class);
                    startActivity(intent);
                    Toast.makeText(AddActivity.this,"Đã lưu chuyến đi của bạn!",Toast.LENGTH_LONG).show();
                }
            }

        });

    }
    private byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
    // Hàm mở camera
    private void captureImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        this.startActivityForResult(intent, REQUEST_ID_IMAGE_CAPTURE);
    }

    private void askPermissionAndCaptureVideo() {
        // Với Android Level >= 23 bạn phải hỏi người dùng cho phép đọc/ghi dữ liệu vào thiết bị.
        if (android.os.Build.VERSION.SDK_INT >= 23) {

            // Kiểm tra quyền đọc/ghi dữ liệu vào thiết bị lưu trữ ngoài.
            int readPermission = ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE);
            int writePermission = ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (writePermission != PackageManager.PERMISSION_GRANTED ||
                    readPermission != PackageManager.PERMISSION_GRANTED) {
                // Nếu không có quyền, cần nhắc người dùng cho phép.
                this.requestPermissions(
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_ID_READ_WRITE_PERMISSION
                );
                return;
            }
        }
        this.captureVideo();
    }
    // Khởi tạo môi trường cho video
    private void captureVideo() {
        // Tạo một Intent không tường minh,
        // để yêu cầu hệ thống mở Camera chuẩn bị quay video.
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        // Thư mục lưu trữ ngoài.
        File dir = Environment.getExternalStorageDirectory();
        if (!dir.exists()) {
            dir.mkdirs();
        }
        // file:///storage/emulated/0/video[...].mp4
        Random r = new Random();
        int number = r.nextInt(999999) + 1;
        String savePath = dir.getAbsolutePath() + "/video"+number+".mp4";
        File videoFile = new File(savePath);
        videoUri = Uri.fromFile(videoFile);
        // Chỉ định vị trí lưu file video khi quay.
        intent.putExtra(MediaStore.EXTRA_OUTPUT, videoUri);
        // Start Activity quay video, và chờ đợi kết quả trả về.
        this.startActivityForResult(intent, REQUEST_ID_VIDEO_CAPTURE);
    }
    // Khi yêu cầu hỏi người dùng được trả về (Chấp nhận hoặc không chấp nhận).
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_ID_READ_WRITE_PERMISSION: {
                // Chú ý: Nếu yêu cầu bị hủy, mảng kết quả trả về là rỗng.
                // Người dùng đã cấp quyền (đọc/ghi).
                if (grantResults.length > 1
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(this, "Permission granted!", Toast.LENGTH_LONG).show();
                    this.captureVideo();
                }
                // Hủy bỏ hoặc bị từ chối.
                else {
                    Toast.makeText(this, "Permission denied!", Toast.LENGTH_LONG).show();
                }
                break;
            }
        }
    }
    // Hàm được gọi khi chụp ảnh hoặc quay video trả về kết qủa
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_ID_IMAGE_CAPTURE) {
            if (resultCode == RESULT_OK) {
                ivImage.setVisibility(View.VISIBLE);
                btnThemAnh.setVisibility(View.GONE);
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                this.ivImage.setImageBitmap(bitmap);
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Action canceled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Action Failed", Toast.LENGTH_LONG).show();
            }
        }
        else if (requestCode == REQUEST_ID_VIDEO_CAPTURE) {
            if (resultCode == RESULT_OK) {
                vvVideo.setVisibility(View.VISIBLE);
                btnThemVideo.setVisibility(View.GONE);
                Uri videoUri = data.getData();
                Log.i("MyLog", "Đã lưu video tại: " + videoUri);
                Toast.makeText(this, "Đã lưu video:\n" +
                        videoUri, Toast.LENGTH_LONG).show();
                this.vvVideo.setVideoURI(videoUri);
                this.vvVideo.start();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Action Cancelled.",
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Action Failed",
                        Toast.LENGTH_LONG).show();
            }
        }
    }
}
