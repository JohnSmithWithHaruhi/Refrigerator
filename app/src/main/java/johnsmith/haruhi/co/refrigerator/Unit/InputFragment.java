package johnsmith.haruhi.co.refrigerator.Unit;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import johnsmith.haruhi.co.refrigerator.R;

/**
 * Created by wj on 16/6/28.
 */
public class InputFragment extends DialogFragment {

    private InputFragmentListener listener;
    private Button button;
    private EditText editText;

    public interface InputFragmentListener {
        void onTextSet(String text);
    }

    public void setListener(InputFragmentListener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input, container, false);
        button = (Button) view.findViewById(R.id.input_Button);
        editText = (EditText) view.findViewById(R.id.input_ET);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onTextSet(editText.getText().toString());
                editText.setText("");
            }
        });
        return view;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setTitle("Enter something");

        return dialog;
    }

}
