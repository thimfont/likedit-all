package br.com.likedit.core.exception;

public class UserNotFoundException extends BusinessException {

    private long userId;

    public UserNotFoundException(final long userId) {
        super(Codes.USER_NOT_FOUND);
        this.userId = userId;
    }

    @Override
    public String getMessage() {
        return String.format("User wasn't found for id #%d", userId);
    }
}
