package cn.hotwoo.alien.servicelife.moudel.user;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.beam.bijection.RequiresPresenter;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.hotwoo.alien.servicelife.R;
import cn.hotwoo.alien.servicelife.app.BaseActivity;
import cn.hotwoo.alien.servicelife.model.bean.User;
import cn.hotwoo.alien.servicelife.util.FileManager;
import cn.hotwoo.alien.servicelife.util.RecentTimeFormat;
import cn.hotwoo.alien.servicelife.util.TimeFormat;
import cn.hotwoo.alien.servicelife.util.Utils;

/**
 * Created by alien on 2015/8/14.
 */
@RequiresPresenter(ModifyInfoPresenter.class)
public class ModifyInfoActivity extends BaseActivity<ModifyInfoPresenter> {

    @Bind(R.id.face)
    SimpleDraweeView face;
    @Bind(R.id.name)
    TextView name;
    @Bind(R.id.viewName)
    LinearLayout viewName;
    @Bind(R.id.gender)
    TextView gender;
    @Bind(R.id.age)
    TextView age;
    @Bind(R.id.sign)
    TextView sign;
    @Bind(R.id.viewSign)
    LinearLayout viewSign;
    @Bind(R.id.school)
    TextView school;
    @Bind(R.id.viewSchool)
    LinearLayout viewSchool;
    @Bind(R.id.birth)
    TextView birth;
    @Bind(R.id.viewBirth)
    LinearLayout viewBirth;
    @Bind(R.id.major)
    TextView major;
    @Bind(R.id.viewMajor)
    LinearLayout viewMajor;
    @Bind(R.id.phone)
    TextView phone;
    @Bind(R.id.viewPhone)
    LinearLayout viewPhone;
    @Bind(R.id.qq)
    TextView qq;
    @Bind(R.id.viewQQ)
    LinearLayout viewQQ;
    @Bind(R.id.intro)
    EditText intro;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.viewGender)
    LinearLayout viewGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_modifydata);
        ButterKnife.bind(this);
        setTitle("修改信息");
        face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ModifyInfoActivity.this, ModifyFaceActivity.class));
            }
        });
        viewName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNameDialog();
            }
        });
        viewSchool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSchoolDialog();
            }
        });
        viewSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSignDialog();
            }
        });
        viewBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBirthDialog();
            }
        });
        viewMajor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMajorDialog();
            }
        });
        viewPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPhoneDialog();
            }
        });
        viewQQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showQQDialog();
            }
        });
        viewGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showGenderDialog();
            }
        });

    }

    public void setData(User data) {
        if (data.getFace() != null)
            face.setImageURI(Uri.parse(data.getFace()));
        name.setText(data.getName());
        if (data.getGender() == 0)
            gender.setText("女");
        else gender.setText("男");
        sign.setText(data.getSign());
        birth.setText(new TimeFormat().toString(new RecentTimeFormat(), data.getBirth() * 1000));
        age.setText(data.getAge() + "");
        school.setText(data.getSchool());
        major.setText(data.getMajor());
        phone.setText(data.getPhone() + "");
        qq.setText(data.getQq() + "");
        intro.setText(data.getIntro());
    }

    public void showGenderDialog() {

    }

    public void showNameDialog() {

    }

    public void showSignDialog() {

    }

    public void showSchoolDialog() {

    }

    public void showBirthDialog() {
        final Calendar calendar = Calendar.getInstance();
        final int CURRENT_YEAR = calendar.get(Calendar.YEAR);
        final int CURRENT_MONTH = calendar.get(Calendar.MONTH);
        final int CURRENT_DAY = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog datePickerDialog, int i, int i1, int i2) {
                        calendar.set(i, i1, i2);
                        if (calendar.getTimeInMillis() > System.currentTimeMillis()) {
                            Utils.Toast("选择错误，请重新选择");
                            return;
                        }
                        getPresenter().setBirth(calendar.getTimeInMillis() / 1000);
                        birth.setText(new TimeFormat().toString(new RecentTimeFormat(), calendar.getTimeInMillis()));
                        if (CURRENT_MONTH - calendar.get(Calendar.MONTH) > 0) {
                            getPresenter().setAge(CURRENT_YEAR - calendar.get(Calendar.YEAR));
                        } else if (CURRENT_MONTH == calendar.get(Calendar.MONTH) && CURRENT_DAY < calendar.get(Calendar.DAY_OF_MONTH)) {
                            getPresenter().setAge(CURRENT_YEAR - calendar.get(Calendar.YEAR) - 1);
                        } else if (CURRENT_MONTH == calendar.get(Calendar.MONTH) && CURRENT_DAY > calendar.get(Calendar.DAY_OF_MONTH)) {
                            getPresenter().setAge(CURRENT_YEAR - calendar.get(Calendar.YEAR));
                        } else
                            getPresenter().setAge(CURRENT_YEAR - calendar.get(Calendar.YEAR) - 1);//当前月份比选择的小的情况

                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        dpd.show(getFragmentManager(), "请选择日期");
    }

    public void showMajorDialog() {

    }

    public void showPhoneDialog() {

    }

    public void showQQDialog() {

    }

    public void setAge(int a) {
        age.setText(a + "");
    }

    @Override
    public void finish() {
        super.finish();
        if (getIntent().getStringExtra("info") != null)
            if (getIntent().getStringExtra("info").equals("完善信息"))
                FileManager.getInstance().clearDir(FileManager.Dir.OBJECT);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_user_modify, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.submit) {
            getPresenter().setIntro(intro.getText().toString());
            getPresenter().updateUserInfo();
        }
        return super.onOptionsItemSelected(item);
    }
}
