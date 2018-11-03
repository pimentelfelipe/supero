package dto;

import model.entity.TbTask;

public class TbTaskDTO extends DTOGeneric<TbTask> {

	private static final long serialVersionUID = 2433427566852686176L;

	public TbTaskDTO() {
	}
	
	public TbTaskDTO(TbTask entity) {
		super(entity);
	}
}
