package com.example.adwitiyasingh.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.jar.Manifest;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "Main Activity";

    EditText etNum1;
    EditText etNum2;
    TextView tvResult;
    Button btnAdd;
    Button btnSub;
    Button btnMul;
    Button btnDiv;
    Button btnRtSqr;
    Button btnFact;
    Button btnAns;
    Button btnmemprev;
    Button btnmemnxt;
    Button btnneg;
    Button btnprcnt;
    Button btnclr;
    Button btnpi;
    Button btnxpowy;
    Button btne;
    RelativeLayout mylayout;
    ArrayList<Double> Memory = new ArrayList<>();
    int memposn = -1;

   
//    @Override
//    protected void onStart() {
//        Log.d(TAG, "onStart: ");
//        super.onStart();
//    }
//
//    @Override
//    protected void onResume() {
//        Log.d(TAG, "onResume:");
//        super.onResume();
//    }
//
//    @Override
//    protected void onPause() {
//        Log.d(TAG, "onPause: ");
//        super.onPause();
//    }
//
//    @Override
//    protected void onDestroy() {
//        Log.d(TAG, "onDestroy: ");
//        super.onDestroy();
//    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Log.d(TAG, "onCreate: ");
        etNum1 = (EditText) findViewById(R.id.etNum1);
        etNum2 = (EditText) findViewById(R.id.etNum2);
        tvResult = (TextView) findViewById(R.id.tvResult);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnSub = (Button) findViewById(R.id.btnSub);
        btnMul = (Button) findViewById(R.id.btnMul);
        btnDiv = (Button) findViewById(R.id.btnDiv);
        btnFact = (Button) findViewById(R.id.btnFact);
        btnRtSqr = (Button) findViewById(R.id.btnRtSqr);
        btnAns = (Button) findViewById(R.id.btnAns);
        btnmemprev = (Button) findViewById(R.id.btnmemmin);
        btnmemnxt = (Button) findViewById(R.id.btnmem);
        btnneg = (Button) findViewById(R.id.btnneg);
        btnprcnt = (Button) findViewById(R.id.btnprcnt);
        btnclr = (Button) findViewById(R.id.btnclr);
        btnpi = (Button) findViewById(R.id.btnpi);
        btnxpowy = (Button) findViewById(R.id.btnxpowy);
        btne = (Button) findViewById(R.id.btne);

        etNum1.setHint("Enter a Number.");
        etNum2.setHint("Enter another number.");

        etNum1.setTextColor(Color.WHITE);
        etNum2.setTextColor(Color.WHITE);
        tvResult.setTextColor(Color.WHITE);
        etNum1.setHintTextColor(Color.WHITE);
        etNum2.setHintTextColor(Color.WHITE);

        View.OnClickListener ocl = new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                switch (v.getId()) {
                    case R.id.btnAdd: {
                        computeAndShow("add");
                        break;
                    }
                    case R.id.btnSub: {
                        computeAndShow("sub");
                        break;
                    }
                    case R.id.btnMul: {
                        computeAndShow("mul");
                        break;
                    }
                    case R.id.btnDiv: {
                        computeAndShow("div");
                        break;
                    }
                    case R.id.btnFact: {
                        computeAndShow("fact");
                        break;
                    }
                    case R.id.btnRtSqr: {
                        computeAndShow("rt");
                        break;
                    }
                    case R.id.btnAns: {
                        String rslt = tvResult.getText().toString();
                        for (int i = 0; i < rslt.length(); i++) {
                            Character now = rslt.charAt(i);
                            if (!Character.isDigit(now) && !now.equals('.') && !now.equals('-')&&!now.equals('E')) {
                                Toast.makeText(getApplicationContext(), "Result is not a number!", Toast.LENGTH_SHORT).show();
                                tvResult.setText("Result.");
                                return;
                            }
                        }
                        etNum1.setText(String.valueOf(rslt));
                        break;
                    }
                    case R.id.btnmemmin: {
                        memprev();
                        break;
                    }
                    case R.id.btnmem: {
                        memnext();
                        break;
                    }
                    case R.id.btnneg: {
                        String ed_text1 = etNum1.getText().toString();
                        if (ed_text1.isEmpty()) {
                            etNum1.setText("-");
                            break;
                        } else if (ed_text1.equals(".")) {
                            Toast.makeText(getApplicationContext(), "Invalid action.", Toast.LENGTH_SHORT).show();
                            break;
                        } else if (ed_text1.charAt(0) == '-') {
                            if (ed_text1.length() > 1) {
                                etNum1.setText(ed_text1.substring(1));
                                break;
                            } else {
                                etNum1.setText("");
                                break;
                            }
                        } else {
                            Double valnow = Double.parseDouble(ed_text1);
                            valnow = valnow - (2 * valnow);
                            etNum1.setText(String.valueOf(valnow));
                            break;
                        }
                    }
                    case R.id.btnprcnt: {
                        computeAndShow("prcnt");
                        break;
                    }
                    case R.id.btnclr: {
                        etNum1.setText("");
                        Toast.makeText(getApplicationContext(), "Cleared!", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case R.id.btne: {
                        etNum1.setText(String.valueOf("2.71828182846"));
                        break;
                    }
                    case R.id.btnpi: {
                        etNum1.setText(String.valueOf("3.14159265359"));
                        break;
                    }
                    case R.id.btnxpowy: {
                        computeAndShow("xpowy");
                        break;
                    }
                    default: {
                        break;
                    }
                }
            }
        };
        View.OnLongClickListener olcl = new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                switch (v.getId()) {
                    case R.id.btnAdd: {
                        computeAndShow("addrev");
                        return true;
                    }
                    case R.id.btnSub: {
                        computeAndShow("subrev");
                        return true;
                    }
                    case R.id.btnMul: {
                        computeAndShow("mulrev");
                        return true;
                    }
                    case R.id.btnDiv: {
                        computeAndShow("divrev");
                        return true;
                    }
                    case R.id.btnRtSqr: {
                        computeAndShow("sqr");
                        return true;
                    }
                    case R.id.btnFact: {
                        computeAndShow("factscnd");
                        return true;
                    }
                    case R.id.btnAns: {
                        String rslt = tvResult.getText().toString();
                        for (int i = 0; i < rslt.length(); i++) {
                            Character now = rslt.charAt(i);
                            if (!Character.isDigit(now) && !now.equals('.') && !now.equals('-')&&!now.equals('E')) {
                                Toast.makeText(getApplicationContext(), "Result is not a number!", Toast.LENGTH_SHORT).show();
                                tvResult.setText("Result.");
                                return true;
                            }
                        }
                        etNum2.setText(String.valueOf(rslt));
                        return true;
                    }
                    case R.id.btnmem:
                    case R.id.btnmemmin: {
                        eraseMemory();
                        return true;
                    }
                    case R.id.btnneg: {
                        String ed_text2 = etNum2.getText().toString();
                        if (ed_text2.isEmpty()) {
                            etNum2.setText("-");
                            return true;
                        } else if (ed_text2.equals(".")) {
                            Toast.makeText(getApplicationContext(), "Invalid action.", Toast.LENGTH_SHORT).show();
                            return true;
                        } else if (ed_text2.charAt(0) == '-') {
                            if (ed_text2.length() > 1) {
                                etNum2.setText(ed_text2.substring(1));
                                return true;
                            } else {
                                etNum2.setText("");
                                return true;
                            }
                        } else {
                            Double valnow = Double.parseDouble(ed_text2);
                            valnow = valnow - (2 * valnow);
                            etNum2.setText(String.valueOf(valnow));
                            return true;
                        }
                    }
                    case R.id.btnprcnt: {
                        computeAndShow("prcntrev");
                        return true;
                    }
                    case R.id.btnclr: {
                        etNum2.setText("");
                        Toast.makeText(getApplicationContext(), "Cleared!", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                    case R.id.btne: {
                        etNum2.setText(String.valueOf("2.71828182846"));
                        return true;
                    }
                    case R.id.btnpi: {
                        etNum2.setText(String.valueOf("3.14159265359"));
                        return true;
                    }
                    case R.id.btnxpowy: {
                        computeAndShow("ypowx");
                        return true;
                    }
                    default: {
                        return false;
                    }
                }

            }
        };


        btnAdd.setOnClickListener(ocl);
        btnAdd.setOnLongClickListener(olcl);
        btnSub.setOnClickListener(ocl);
        btnSub.setOnLongClickListener(olcl);
        btnMul.setOnClickListener(ocl);
        btnMul.setOnLongClickListener(olcl);
        btnDiv.setOnClickListener(ocl);
        btnDiv.setOnLongClickListener(olcl);
        btnFact.setOnClickListener(ocl);
        btnFact.setOnLongClickListener(olcl);
        btnRtSqr.setOnClickListener(ocl);
        btnRtSqr.setOnLongClickListener(olcl);
        btnAns.setOnClickListener(ocl);
        btnAns.setOnLongClickListener(olcl);
        btnmemprev.setOnClickListener(ocl);
        btnmemprev.setOnLongClickListener(olcl);
        btnmemnxt.setOnClickListener(ocl);
        btnmemnxt.setOnLongClickListener(olcl);
        btnneg.setOnClickListener(ocl);
        btnneg.setOnLongClickListener(olcl);
        btnprcnt.setOnClickListener(ocl);
        btnprcnt.setOnLongClickListener(olcl);
        btnclr.setOnClickListener(ocl);
        btnclr.setOnLongClickListener(olcl);
        btnpi.setOnClickListener(ocl);
        btnpi.setOnLongClickListener(olcl);
        btnxpowy.setOnClickListener(ocl);
        btnxpowy.setOnLongClickListener(olcl);
        btne.setOnClickListener(ocl);
        btne.setOnLongClickListener(olcl);


    }


    private void computeAndShow(String action) {
        String ed_text1 = etNum1.getText().toString();
        String ed_text2 = etNum2.getText().toString();

        if (ed_text1.isEmpty() && ed_text2.isEmpty()) {
            Toast.makeText(getApplicationContext(), "No Input.", Toast.LENGTH_SHORT).show();
            return;
        } else if (ed_text1.equals(".") || ed_text2.equals(".") || ed_text1.equals("-") || ed_text2.equals("-") || ed_text1.equals("-.") || ed_text2.equals("-.")) {
            Toast.makeText(getApplicationContext(), "Wrong Input.", Toast.LENGTH_SHORT).show();
            if (ed_text1.equals(".") || ed_text1.equals("-") || ed_text1.equals("-.")) {
                etNum1.setText("");
            }
            if (ed_text2.equals(".") || ed_text2.equals("-") || ed_text2.equals("-.")) {
                etNum2.setText("");
            }
            return;
        } else if (ed_text1.isEmpty() && !action.equals("factscnd")) {
            Toast.makeText(getApplicationContext(), "Partial Input.", Toast.LENGTH_SHORT).show();
            return;
        } else if (ed_text2.isEmpty() && !action.equals("fact") && !action.equals("rt") && !action.equals("sqr")) {
            Toast.makeText(getApplicationContext(), "Partial Input.", Toast.LENGTH_SHORT).show();
            return;
        } else {
            Double num1 = 0.0;
            Double num2 = 0.0;
            if (!action.equals("factscnd")) {
                num1 = Double.parseDouble(ed_text1);
            }
            if (!action.equals("fact") && !action.equals("rt") && !action.equals("sqr")) {
                num2 = Double.parseDouble(ed_text2);
            }
            Double ans = 0.0;
            switch (action) {
                case "add":
                case "addrev": {
                    ans = num1 + num2;
                    tvResult.setText(ans.toString());
                    if (action.equals("addrev")) {
                        Toast.makeText(getApplicationContext(), "Addition is commutative!", Toast.LENGTH_SHORT).show();

                    }
                    break;

                }
                case "sub":
                case "subrev": {
                    ans = action.equals("sub") ? num1 - num2 : num2 - num1;
                    tvResult.setText(ans.toString());
                    break;

                }
                case "mul":
                case "mulrev": {
                    ans = num1 * num2;
                    tvResult.setText(ans.toString());
                    if (action.equals("mulrev")) {
                        Toast.makeText(getApplicationContext(), "Multiplication is commutative!", Toast.LENGTH_SHORT).show();

                    }
                    break;
                }
                case "div":
                case "divrev": {
                    Double iszero = action.equals("div") ? num2 : num1;
                    if (iszero == 0.0) {
                        Toast.makeText(getApplicationContext(), "Cannot divide by zero.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    ans = action.equals("div") ? num1 / num2 : num2 / num1;
                    tvResult.setText(ans.toString());
                    break;
                }
                case "fact":
                case "factscnd": {
                    if (action.equals("fact") && num1 % 1 > 0 || num1 < 0) {
                        Toast.makeText(getApplicationContext(), "Only whole numbers have factorials!", Toast.LENGTH_SHORT).show();
                        return;
                    } else if (action.equals("factscnd") && num2 % 1 > 0 || num2 < 0) {
                        Toast.makeText(getApplicationContext(), "Only whole numbers have factorials!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    ans = 1.0;
                    for (Double i = action.equals("fact") ? num1 : num2; i > 0; i--) {
                        ans *= i;
                    }
                    tvResult.setText(ans.toString());
                    break;
                }
                case "rt": {
                    if (ed_text1.charAt(0) == '-') {
                        Toast.makeText(getApplicationContext(), "Only positive numbers have roots!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    ans = Math.sqrt(num1);
                    tvResult.setText(ans.toString());
                    break;
                }
                case "sqr": {
                    ans = num1 * num1;
                    tvResult.setText(ans.toString());
                    break;
                }
                case "prcnt":
                case "prcntrev": {

                    ans = (num1 / 100) * num2;
                    tvResult.setText(ans.toString());
                    break;
                }
                case "xpowy":
                case "ypowx": {
                    if (num2 == 0) {
                        tvResult.setText("0");
                        break;
                    } else if (action.equals("xpowy") && num2 % 1 > 0 || num2 < 0) {
                        Toast.makeText(getApplicationContext(), "Power can only be a whole number.", Toast.LENGTH_SHORT).show();
                        break;
                    } else if (action.equals("ypowx") && num1 % 1 > 0 || num1 < 0) {
                        Toast.makeText(getApplicationContext(), "Power can only be a whole number.", Toast.LENGTH_SHORT).show();
                        break;
                    } else {
                        Double result = action.equals("xpowy") ? num1 : num2;
                        ans = 1.0;
                        for (Double i = 0.0; i < (action.equals("xpowy") ? num2 : num1); i++) {
                            ans = ans * result;
                        }
                        tvResult.setText(ans.toString());
                        break;
                    }
                }


            }
            addToMemory();
            Intent i = new Intent(MainActivity.this,ResultActivity.class);
            i.putExtra("result", ans.toString());
            startActivity(i);
        }
    }

    private void addToMemory() {
        String rslt = tvResult.getText().toString();
        for (int i = 0; i < rslt.length(); i++) {
            Character now = rslt.charAt(i);
            if (!Character.isDigit(now) && !now.equals('.')) {
                return;
            }
        }
        Memory.add(Double.parseDouble(rslt));
        memposn = Memory.size() - 1;
        return;
    }

    private void memprev() {
        memposn--;
        if (Memory.isEmpty()) {
            memposn++;
            Toast.makeText(getApplicationContext(), "No memory!", Toast.LENGTH_SHORT).show();
            return;
        } else if (memposn == -1) {
            Toast.makeText(getApplicationContext(), "No previous memory!", Toast.LENGTH_SHORT).show();
            memposn++;
            return;
        } else if (memposn < -1) {
            memposn++;
        } else {
            tvResult.setText(Memory.get(memposn).toString());
            return;
        }
    }

    private void memnext() {
        memposn++;
        if (Memory.isEmpty()) {
            memposn--;
            Toast.makeText(getApplicationContext(), "No memory!", Toast.LENGTH_SHORT).show();
            return;
        } else if (memposn == Memory.size()) {
            Toast.makeText(getApplicationContext(), "This is the latest entry!", Toast.LENGTH_SHORT).show();
            memposn--;
        } else if (memposn > Memory.size()) {
            memposn--;
        } else {
            tvResult.setText(Memory.get(memposn).toString());
            return;
        }
    }

    private void eraseMemory() {
        if (Memory.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Memory already empty.", Toast.LENGTH_SHORT).show();
        } else {
            Memory = new ArrayList<>();
            memposn = -1;
            Toast.makeText(getApplicationContext(), "Memory Cleared!", Toast.LENGTH_SHORT).show();
            tvResult.setText("Result.");
        }
    }


}


