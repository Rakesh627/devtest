package com.build.devtest;

import java.util.ArrayList;
import java.util.List;

public class ParentViewMapperImpl implements ParentViewMapper {

	@Override
	public List<ParentView> mapRowsToViews(List<ParentRow> parentRows, List<ChildRow> childRows) {
		
		
		//create child row and sequentially create parent view
		List<ParentView> resultView = new ArrayList<ParentView>();
		List<String> parentID = new ArrayList<String>();
		int indexToAdd =0;
		for(ParentRow parent : parentRows)
		{
			parentID.add(parent.getParentId().trim().toLowerCase());
			resultView.add(new ParentView(parent.getFirstName(), parent.getLastName(), parent.getAge(), parent.getParentId(), new ArrayList<ChildView>()));
		}
		for(ChildRow child: childRows)
		{
			if(parentID.contains((String)child.getParentId().trim().toLowerCase()))
			{
				indexToAdd = parentID.indexOf((String)child.getParentId().trim().toLowerCase());
				resultView.get(indexToAdd).getChildViews().add(new ChildView(child.getFirstName(), child.getLastName(), child.getAge(), child.getChildId(), resultView.get(indexToAdd)));
			}
		}
			
		return resultView;
	}

}
