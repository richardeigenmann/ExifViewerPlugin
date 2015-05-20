package org.richinet.ExifViewerPlugin;

import java.io.IOException;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.MIMEResolver;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectExistsException;
import org.openide.loaders.MultiDataObject;
import org.openide.loaders.MultiFileLoader;
import org.openide.nodes.Node;
import org.openide.util.NbBundle.Messages;

@Messages( {
    "LBL_Jpg_LOADER=Files of Jpg"
} )
@MIMEResolver.ExtensionRegistration(
         displayName = "#LBL_Jpg_LOADER",
        mimeType = "image/jpeg",
        extension = { "jpg", "JPG", "Jpg", "JPEG", "jpeg", "Jpeg" },
        position=109
)

@DataObject.Registration(
         mimeType = "image/jpeg",
        // image from http://uxrepo.com/icon/camera-by-linecons
        // Open Source License by Sergey Shmidt
        iconBase = "org/richinet/ExifViewerPlugin/camera-16-000000.png",
        displayName = "#LBL_Jpg_LOADER",
        position = 1
)

public class JpgDataObject extends MultiDataObject {

    
    public JpgDataObject( FileObject fileObject, MultiFileLoader loader )
            throws DataObjectExistsException, IOException {
        super( fileObject, loader );
    }

    @Override
    protected int associateLookup() {
        return 1;
    }

    @Override
    protected Node createNodeDelegate() {
        return new JpgDataNode ( this );
    }

    
}
