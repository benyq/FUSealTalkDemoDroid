package cn.rongcloud.im.ui.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import cn.rongcloud.im.ui.adapter.models.SearchModel;
import cn.rongcloud.im.ui.interfaces.OnChatItemClickListener;
import java.util.List;

public class SearchConversationFragment extends SearchBaseFragment {

    public void init(OnChatItemClickListener onChatItemClickListener) {
        init(onChatItemClickListener, null, null, null, null);
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        viewModel
                .getConversationSearch()
                .observe(
                        this,
                        new Observer<List<SearchModel>>() {
                            @Override
                            public void onChanged(List<SearchModel> searchModels) {
                                updateData(searchModels);
                            }
                        });
        if (!TextUtils.isEmpty(initSearch)) {
            search(initSearch);
        }
        return view;
    }

    @Override
    public void search(String search) {
        super.search(search);
        if (viewModel != null) {
            viewModel.searchConversation(search);
        }
    }
}
