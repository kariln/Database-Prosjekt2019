package modeller;

import java.sql.Connection;

public interface ActiveDomainObject {
	void initialize(Connection conn);
	void refresh(Connection conn);
	void save(Connection conn);
	void add(Connection conn);
}