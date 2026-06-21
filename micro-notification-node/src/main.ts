import fastify from 'fastify';
import * as nodemailer from 'nodemailer';

const server = fastify({ logger: true });

// 1. CONFIGURACIÓN REAL PARA GMAIL TLS SEGURO
const transporter = nodemailer.createTransport({
    service: 'gmail',
    host: '://gmail.com',
    port: 587,
    secure: false, // false para puerto 587 (TLS)
    auth: {
        user: 'tu-correo-completo@gmail.com', // Coloca aquí tu cuenta de Gmail real
        pass: 'abcd efgh ijkl mnop'  // Tu Contraseña de Aplicación de Google de 16 caracteres
    },
    tls: {
        rejectUnauthorized: false // Permite la comunicación a través del proxy de red de Docker
    }
});

interface MailBody {
    to: string;
    reportName: string;
    generatedBy: string;
}

// 2. Definimos el endpoint de notificación
server.post<{ Body: MailBody }>('/notifications/send-email', async (request, reply) => {
    const authHeader = request.headers['authorization'];

    // Filtro guardián secundario por si acaso
    if (!authHeader) {
        return reply.status(401).send({ error: "Missing Authorization Token" });
    }

    const { to, reportName, generatedBy } = request.body;
    const targetEmail = to || 'tu-correo-completo@gmail.com'; // Por defecto te lo mandará a ti mismo

    const mailOptions = {
        from: '"Ecosistema Microservicios" <tu-correo-completo@gmail.com>',
        to: targetEmail,
        subject: `🚨 Alerta de Sistema: Reporte Generado con éxito`,
        html: `
            <div style="font-family: Arial, sans-serif; border: 1px solid #ddd; padding: 20px; border-radius: 5px;">
                <h2 style="color: #2c3e50;">Notificación del Sandbox Políglota</h2>
                <p>Se ha detectado la exportación exitosa de un archivo binario en el backend.</p>
                <hr style="border: 0; border-top: 1px solid #eee;">
                <ul>
                    <li><b>Nombre del Reporte:</b> <code style="background: #f8f9fa; padding: 2px 5px; border-radius: 3px;">${reportName || 'crudList.xls'}</code></li>
                    <li><b>Usuario Ejecutor (Mapeado del JWT):</b> ${generatedBy || 'Usuario Anónimo'}</li>
                </ul>
                <br>
                <small style="color: #7f8c8d;">Este es un correo automático de producción enviado en tiempo real por Node.js a través de Gmail SMTP.</small>
            </div>
        `
    };

    try {
        // MANDATORIO: Ejecutamos el envío real del correo hacia los servidores de Google
        const info = await transporter.sendMail(mailOptions);

        server.log.info(`[NODE-MAIL] Email enviado con éxito. MessageId: ${info.messageId}`);
        return {
            status: "success",
            message: "Notificación de reporte enviada de forma real a través de Gmail SMTP",
            recipient: targetEmail,
            messageId: info.messageId
        };
    } catch (error) {
        server.log.error(`Error crítico enviando email por Gmail: ${(error as Error).message}`);
        return reply.status(500).send({ error: `Failed to dispatch Gmail notification: ${(error as Error).message}` });
    }
});

// 3. Arrancamos el servidor en el puerto 3000 de la red interna de Docker
const start = async () => {
    try {
        await server.listen({ port: 3000, host: '0.0.0.0' });
        console.log("▲ Micro Notification Node operativo en el puerto 3000");
    } catch (err) {
        server.log.error(err);
        process.exit(1);
    }
};

start();
