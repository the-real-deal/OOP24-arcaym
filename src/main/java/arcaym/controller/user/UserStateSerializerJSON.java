package arcaym.controller.user;

import java.nio.file.Path;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import arcaym.common.utils.file.FileUtils;
import arcaym.model.user.UserStateInfo;
import arcaym.model.user.UserStateInfoUtils;

/**
 * Implementation of {@link UserStateSerializer}. 
 */
public class UserStateSerializerJSON implements UserStateSerializer {

    private static final String EXTENSION = ".json";
    private static final String FILENAME = "user_data";

    private static final Logger LOGGER = LoggerFactory.getLogger(UserStateSerializer.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean save(final UserStateInfo userState) {
        FileUtils.createUserDirectory();
        this.validateFileName(FILENAME);
        return FileUtils.writeFile(
            FILENAME.concat(EXTENSION), 
            FileUtils.USER_FOLDER, 
            new Gson().toJson(userState));
    }

    /* Utility function */
    private Optional<UserStateInfo> load() {
        this.validateFileName(FILENAME);
        final var rawState = FileUtils.readFromPath(this.getPathOf(FILENAME));
        if (rawState.isEmpty()) {
            LOGGER.error("An error occurred while READING '" + FILENAME + "' file.");
            return Optional.empty();
        }
        return FileUtils.convertToObj(UserStateInfo.class, rawState.get());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserStateInfo getUpdatedState() {
        final var previousSave = this.load();
        if (previousSave.isPresent()) {
            return previousSave.get();
        }
        final var defaultState = UserStateInfoUtils.defaultState();
        if (!this.save(defaultState)) {
            LOGGER.error("Cannot save the default state: " + defaultState);
        } else {
            LOGGER.info("It looks like nothing has been saved before. Saving a default user state: " + defaultState);
        }
        return defaultState;
    }

    private Path getPathOf(final String fileName) {
        return Path.of(FileUtils.USER_FOLDER, fileName.concat(EXTENSION));
    }

    private void validateFileName(final String fileName) {
        if (Objects.isNull(fileName) || fileName.isBlank() || fileName.isEmpty()) {
            throw new IllegalArgumentException("Invalid filename!");
        }
    }
}
