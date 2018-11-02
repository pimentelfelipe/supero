package dto;

import model.entity.TbUser;

public class TbUserDTO extends DTOGeneric<TbUser> {

	private static final long serialVersionUID = 2433427566852686176L;

	public TbUserDTO() {
	}
	
	public TbUserDTO(TbUser entity) {
		super(entity);
	}
}
