package com.build.devtest;

import java.util.ArrayList;
import java.util.List;

public class ParentViewMapperImpl implements ParentViewMapper {

	@Override
	public List<ParentView> mapRowsToViews(List<ParentRow> parentRows, List<ChildRow> childRows) {
				
		//the list consisting of all the parents and child mapped views
		List<ParentView> resultView = new ArrayList<ParentView>();
		
		//this list is used for mapping the child's parent id with the parent id object in ParentView list
		List<String> parentID = new ArrayList<String>();
		
		//the index where the child should be added in the ParentView list
		int indexToAdd =0;
		
		//add the parents to the resultView list and add the parent id to parentID list for mapping ParentView objects
		for(ParentRow parent : parentRows)
		{
			parentID.add(parent.getParentId().trim().toLowerCase());
			resultView.add(new ParentView(parent.getFirstName(), parent.getLastName(), parent.getAge(), parent.getParentId(), new ArrayList<ChildView>()));
		}
		
		//add the children to their respective parents
		for(ChildRow child: childRows)
		{
			//see if the childs parent exists in our list
			if(parentID.contains((String)child.getParentId().trim().toLowerCase()))
			{
				//find the index of the parent object in our list
				indexToAdd = parentID.indexOf((String)child.getParentId().trim().toLowerCase());
				
				//add the children to their respective parents
				resultView.get(indexToAdd).getChildViews().add(new ChildView(child.getFirstName(), child.getLastName(), child.getAge(), child.getChildId(), resultView.get(indexToAdd)));
			}
		}
			
		return resultView;
	}

}
