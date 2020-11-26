package data_access.message_access;

import data_access.model_access.ModelDAO_Query;

public interface MessageDAO_Query extends ModelDAO_Query {

	String getListQuery();

	String deleteQuery();
}
