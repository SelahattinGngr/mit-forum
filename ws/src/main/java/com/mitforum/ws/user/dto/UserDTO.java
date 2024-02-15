package com.mitforum.ws.user.dto;

import com.mitforum.ws.user.User;

public class UserDTO {
    
	long id;

	private String  username;

	private String  email;

	String image;

    public UserDTO(User user) {
        setId(user.getId());
        setUsername(user.getUsername());
        setEmail(user.getEmail());
        setImage(user.getImage());
    }

	public String getImage() { return image; }

	public void setImage(String image) { this.image = image; }

	public long getId() { return id; }

	public void setId(long id) { this.id = id; }

	public String getUsername() { return username; }

	public void setUsername(String username) { this.username = username; }

	public String getEmail() { return email; }

	public void setEmail(String email) { this.email = email; }

}