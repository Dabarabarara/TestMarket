package Helpers.UserProfiles;

/**
 * Created by Dima Makieiev on 12.01.2016.
 * Updated by Vladimir V. Klochkov on 31.01.2020.
 */
public interface IUserProfile
{
    <T> T getLogic(Class<T> clazz);
}