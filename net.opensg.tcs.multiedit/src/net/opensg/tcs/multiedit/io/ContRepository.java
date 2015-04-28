package net.opensg.tcs.multiedit.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import net.opensg.tcs.commons.libs.core.TreeItemInfo;

public class ContRepository extends ModelObject {

	private boolean dirty = false;
	public boolean isDirty() {
		return dirty;
	}
	public void setDirty(boolean dirty) {
		this.dirty = dirty;
	}
	
	private static ContRepository singleInstance = null;
	public static ContRepository getInstance() {
		if (ContRepository.singleInstance == null) {
			ContRepository.singleInstance = new ContRepository();
		}
		return ContRepository.singleInstance;
	}
	
//	public static final String dataChangeReason_ModelData = "ModelData";
//	public static final String dataChangeReason_FilePath = "FilePath";
	
	private List<TreeItemInfo> modelData = null;
	public List<TreeItemInfo> getModelData() {
		return this.modelData;
	}
	public void setModelData(List<TreeItemInfo> modelData) {
		Object oldValue = this.modelData;
		this.modelData = modelData;
		this.firePropertyChange("modelData", oldValue, this.modelData);
		this.firePropertyChange("dirty", true, false);
	}

	private String modelFilePath = "";
	public String getModelFilePath() {
		return this.modelFilePath;
	}
	public void setModelFilePath(String modelFilePath) {
		Object oldValue = this.modelFilePath;
		this.modelFilePath = modelFilePath;
		this.firePropertyChange("modelFilePath", oldValue, this.modelFilePath);
		this.firePropertyChange("dirty", true, false);
	}

	public void notifyUpdate() {
		this.firePropertyChange("dirty", false, true);
	}
	
	/**
	 * 저장소로부터 데이터를 읽어온다 (Serialization)
	 * @param dataPath
	 * @return
	 */
	public boolean readData(String filePath) {
		try {
			FileInputStream fis = new FileInputStream(filePath);
			ObjectInputStream ois = new ObjectInputStream(fis);
			this.setModelData((List<TreeItemInfo>)ois.readObject());;
			ois.close();
			this.setModelFilePath(filePath);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * 저장소에 데이터를 저장한다 (Serialization)
	 * @param dataPath
	 * @param dataList
	 * @return
	 */
	public boolean writeData(String filePath) {
		try {
			FileOutputStream fos = new FileOutputStream(filePath);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this.getModelData());
			oos.close();
			this.setModelFilePath(filePath);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
