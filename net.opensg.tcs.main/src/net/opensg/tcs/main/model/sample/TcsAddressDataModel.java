package net.opensg.tcs.main.model.sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.opensg.tcs.main.model.TcsContact;
import net.opensg.tcs.main.model.TcsContactGroup;

public class TcsAddressDataModel {
	
	public static List<TcsContactGroup> BuildSampleContactGroupList() {
		List<TcsContactGroup> resultList = new ArrayList<TcsContactGroup>();
		TcsContactGroup group;
		TcsContact contact;

		ArrayList<String> names = new ArrayList<String>(
			Arrays.asList("OpenSG All", "OnYang", "Gihung", "Sebrance")
		);
		for (String name : names) {
			group = new TcsContactGroup();
			group.Name = name;
			group.Description = name;
			group.Memo = name;
			group.ContactList = new ArrayList<TcsContact>();
			for (int i=0; i<5; i++) {
				contact = new TcsContact();
				contact.Name = String.format("%s-%05d", name, i + 1);
				contact.Email = String.format("%05d@opensg.net", i + 1);
				contact.PhoneOffice = String.format("041-000-%05d", i + 1);
				contact.PhoneMobile = String.format("010-000-%05d", i + 1);
				contact.Address = "Buksu-ri 100, BaeBang-eup, Asan-si";
				group.ContactList.add(contact);
			}
			resultList.add(group);
		}
		return resultList;
	}
}
