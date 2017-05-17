package com.example.alexandrova.notificator.dialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.example.inquallity.notificator.R;

public class AddNewNotificationDialog extends DialogFragment implements View.OnClickListener {

    private EditText mTime;

    private EditText mText;

    private TextView mSave;

    private TextView mCancel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.d_add_notification, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        mTime = (EditText) view.findViewById(R.id.etNotificationTime);
        mText = (EditText) view.findViewById(R.id.etNotificationText);
        mSave = (TextView) view.findViewById(R.id.btnSave);
        mCancel = (TextView) view.findViewById(R.id.btnCancel);
        mSave.setOnClickListener(this);
        mCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnSave) {
            if (getActivity() instanceof OnNotificationSaveListener) {
                ((OnNotificationSaveListener) getActivity()).onSave(
                        mTime.getText().toString(),
                        mText.getText().toString());
                dismiss();
            }
        }
    }

    public interface OnNotificationSaveListener {

        void onSave(@NonNull String time, @NonNull String text);
    }

}
