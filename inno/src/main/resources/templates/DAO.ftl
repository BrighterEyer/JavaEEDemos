package ${package_name}.repository.mybatis;

import java.util.List;
import ${package_name}.dto.${table_name}DTO;
import ${package_name}.model.${table_name};

/**
* 描述：${table_annotation}DAO
* @author ${author}
* @date ${date}
*/
public interface ${table_name}DAO{

    ${table_name}DTO findDTOById(String id);
	List<${table_name}> find${table_name}Page(${table_name}DTO ${table_name?uncap_first}Dto);

}