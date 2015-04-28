package net.opensg.tcs.multiedit.io;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Observable;

import net.opensg.tcs.commons.libs.core.TreeItemInfo;

public class ContRepo extends Observable {

	//-----------------------------------------------------------------------
	private PropertyChangeSupport changeSupport = new PropertyChangeSupport(
			this);

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		changeSupport.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		changeSupport.removePropertyChangeListener(listener);
	}

	public void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		changeSupport.addPropertyChangeListener(propertyName, listener);
	}

	public void removePropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		changeSupport.removePropertyChangeListener(propertyName, listener);
	}

	protected void firePropertyChange(String propertyName, Object oldValue,
			Object newValue) {
		changeSupport.firePropertyChange(propertyName, oldValue, newValue);
	}
	
	
	//-----------------------------------------------------------------------
	private static ContRepo singleInstance = null;
	public static ContRepo getInstance() {
		if (ContRepo.singleInstance == null) {
			ContRepo.singleInstance = new ContRepo();
		}
		return ContRepo.singleInstance;
	}
	
	public static final String dataChangeReason_ModelData = "ModelData";
	public static final String dataChangeReason_FilePath = "FilePath";
	
	private List<TreeItemInfo> modelData = null;
	public List<TreeItemInfo> getModelData() {
		return this.modelData;
	}
	public void setModelData(List<TreeItemInfo> modelData) {
		this.modelData = modelData;
		this.setChanged();
		this.notifyObservers(ContRepo.dataChangeReason_ModelData);
	}

	private String modelFilePath = "";
	public String getModelFilePath() {
		return this.modelFilePath;
	}
	public void setModelFilePath(String modelFilePath) {
		this.modelFilePath = modelFilePath;
		this.setChanged();
		this.notifyObservers(ContRepo.dataChangeReason_FilePath);
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
