package com.example.wangwei.testgson;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class FunctionAdapter extends RecyclerView.Adapter<FunctionAdapter.ViewHolder>{

	private Context mContext;
	private List<String> funcBtns;

	public FunctionAdapter(Context context, List<String> btns) {
		this.mContext = context;
		funcBtns = btns;
	}
	
	@Override
	public int getItemCount() {
		return funcBtns.size();
	}

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, int position) {
		final String functionButton = funcBtns.get(position);
		if(functionButton == null) return;
		viewHolder.funcName.setText("Text");
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
		View view = LayoutInflater.from(mContext).inflate(R.layout.tv, null);
		ViewHolder viewHolder = new ViewHolder(view);
		return viewHolder;
	}
	
	class ViewHolder extends RecyclerView.ViewHolder{
		
		TextView funcName;

		public ViewHolder(View itemView) {
			super(itemView);
			funcName = (TextView) itemView.findViewById(R.id.text);
		}
	}
	

}
